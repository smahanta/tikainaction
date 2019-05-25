package com.learntika.tikainaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.tika.Tika;

public class LuceneIndexer {
	
	private final Tika tika;
	private final IndexWriter writer;
	
	public LuceneIndexer(Tika tika, IndexWriter writer) {
		super();
		this.tika = tika;
		this.writer = writer;
	}

	public void indexDocument(File file) throws Exception {
		
		Document doc = new Document();
		doc.add(new StringField("name", file.getName(), Store.YES));
		doc.add(new StringField("value",tika.parseToString(file), Store.YES));
		
		writer.addDocument(doc);
		
	}

	public static void main(String[] args) {
		Path path = Paths.get("E:\\luceneindex");
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(new SimpleFSDirectory(path), indexWriterConfig);
			LuceneIndexer luceneIndexer = new LuceneIndexer(new Tika(), writer);
			luceneIndexer.indexDocument(new File(args[0]));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
