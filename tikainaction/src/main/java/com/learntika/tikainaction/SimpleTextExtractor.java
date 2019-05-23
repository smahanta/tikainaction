package com.learntika.tikainaction;

import java.io.File;
import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

public class SimpleTextExtractor {

	public static void main(String[] args) throws IOException, TikaException {
		Tika tika = new Tika();
		// Parse all given files and print out the extracted text content
		for (String file : args) {
		String text = tika.parseToString(new File(file));
		System.out.print(text);
		}

	}

}
