package com.learntika.tikainaction;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
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
		// TODO Auto-generated method stub

	}

}
