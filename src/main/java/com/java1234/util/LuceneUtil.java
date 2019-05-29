package com.java1234.util;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.java1234.entity.Blog;
import com.java1234.entity.SearchResult;

/**
 * 博客全文检索工具类
 * @author gucaini
 *
 */
public class LuceneUtil {
	
	/**
	 * 获取IndexWriter写索引流
	 * @return
	 * @throws IOException
	 */
	private static IndexWriter getWriter() throws IOException{
		
		//获取lucene索引存储路径
		Directory directory = FSDirectory.open(Paths.get(PropertiesUtil.getValue("lucene.path")));
		
		//创建中文分词器
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		
		//创建配置
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		
		//实例化IndexWriter
		IndexWriter iw = new IndexWriter(directory, conf);
		
		return iw;
	}
	
	/**
	 * 创建博客索引
	 * @param blog 需要添加索引的博客信息
	 * @throws IOException
	 */
	public static void blogIndex(Blog blog) throws IOException{
		
		IndexWriter iw = getWriter();
		
		//创建域
		Document dc = new Document();
		
		//添加字段
		dc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
		
		dc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
		
		dc.add(new StringField("releaseTimeStr",DateUtil.DateToStr(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		
		dc.add(new TextField("content", blog.getContentNoTag(),Field.Store.YES));
		
		iw.addDocument(dc);
		
		iw.close();
		
	}
	
	/**
	 * 创建IndexReader输入流
	 * @return
	 * @throws IOException
	 */
	private static IndexReader getIndexReader() throws IOException{
		//获取lucene索引存储路径
		Directory directory = FSDirectory.open(Paths.get(PropertiesUtil.getValue("lucene.path")));
		
		IndexReader ir = DirectoryReader.open(directory);
		
		return ir;
	}
	
	/**
	 * 创建结果高亮显示
	 * @param query
	 * @return
	 */
	private static Highlighter getHighlighter(Query query){
		
		//搜索得分器
		QueryScorer scorer = new QueryScorer(query);
		
		//拆分器，会将一段内容拆分成多个片段，按得分的高低排序
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		
		//用户输入的搜索词在搜索的结果当中进行高亮样式显示，这里可自定义显示的样式
		SimpleHTMLFormatter shf = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
		
		//创建高亮器
		Highlighter highlighter = new Highlighter(shf, scorer);
		
		//拆分片段，设置摘要
		highlighter.setTextFragmenter(fragmenter);
		
		return highlighter;
		
	}
	
	/**
	 * 通过索引查询博客信息
	 * @param q 查询的关键词
	 * @param page 当前页数
	 * @param pageSize 每页显示条数
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	public static SearchResult searchBlog(String q,int page,int pageSize) throws IOException, ParseException, InvalidTokenOffsetsException{
		
		IndexReader reader = getIndexReader();
		
		IndexSearcher is = new IndexSearcher(reader);
		
		//创建中文分词器
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		
		//分别在标题和内容中分别查找
		Query query1 = new QueryParser("title",analyzer).parse(q);
		
		Query query2 = new QueryParser("content",analyzer).parse(q);
		
		//lunce5以后以这种方式来创建BooleanQuery类
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		
		//将2个搜索条件拼接起来，其中SHOULD代表或，MUST代表且，NOT MUST代表不包含
		booleanQuery.add(query1, BooleanClause.Occur.SHOULD);
				
		booleanQuery.add(query2,BooleanClause.Occur.SHOULD);
		
		//执行查询
		TopDocs topDocs = is.search(booleanQuery.build(), 100);
		
		//获取高亮显示
		Highlighter highlighter = getHighlighter(booleanQuery.build());
		
		SearchResult result = new SearchResult();
		
		List<Blog> blogs = new ArrayList<Blog>();
		
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		for(ScoreDoc scoreDoc : scoreDocs){
		
			//分页计算
			if(scoreDoc.doc>=(page-1)*pageSize && scoreDoc.doc<page*pageSize){
				
				Document dc = is.doc(scoreDoc.doc);
				
				Blog blog = new Blog();
				
				blog.setId(Integer.parseInt(dc.get("id")));
				
				blog.setReleaseTimeStr(dc.get("releaseTimeStr"));
				
				String title=dc.get("title");

				String content=dc.get("content");
				
				if(title!=null){
					//通过分词器获取输入流
					TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
					
					String hTitle=highlighter.getBestFragment(tokenStream, title);
					
					//如果查询出来的标题不包含高亮片段，则设置为原标题
					if(StringUtil.isEmpty(hTitle)){
						
						blog.setTitle(title);
						
					}else{
						
						blog.setTitle(hTitle);
						
					}

				}
				
				if(content!=null){
					//通过分词器获取输入流
					TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
					
					String hcontent=highlighter.getBestFragment(tokenStream, content);
					
					//如果查询出来的内容不包含高亮片段，则设置为原内容
					if(StringUtil.isEmpty(hcontent)){
						
						//如果原内容的长度超过了155字符，并截取155个字符显示
						if(content.length()>=155){
							
							blog.setContent(content.substring(0, 155)+"...");
							
						}
						
						blog.setContent(content);
						
					}else{
						
						blog.setContent(hcontent);
						
					}
					
				}
				
				blogs.add(blog);
			}
			
		}
		
		//将博客信息和总记录数都封装进实体返回
		result.setBlogs(blogs);
		
		result.setResultCount(topDocs.scoreDocs.length);
		
		reader.close();
		
		return result;
		
	}
	
	/**
	 * 删除博客索引
	 * @param ids
	 * @throws IOException 
	 */
	public static void deleteIndex(String[] ids) throws IOException{
		
		IndexWriter writer =  getWriter();
		
		for(String str:ids){
			
			writer.deleteDocuments(new Term("id",str));
			
		}
		
		//强制删除并合并
		writer.forceMergeDeletes();
		
		//提交事务
		writer.commit();
		
		writer.close();
		
	}
	
	/**
	 * 修改博客索引
	 * @param blog 需要修改索引的博客信息
	 * @throws IOException
	 */
	public static void updateBlogIndex(Blog blog) throws IOException{
		
		IndexWriter iw = getWriter();
		
		//创建域
		Document dc = new Document();
		
		//添加字段
		dc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
		
		dc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
		
		dc.add(new StringField("releaseTimeStr",DateUtil.DateToStr(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		
		dc.add(new TextField("content", blog.getContentNoTag(),Field.Store.YES));
		
		iw.updateDocument(new Term("id",String.valueOf(blog.getId())), dc);
		
		iw.close();
		
	}
}
