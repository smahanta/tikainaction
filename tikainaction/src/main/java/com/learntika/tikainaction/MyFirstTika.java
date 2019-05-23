package com.learntika.tikainaction;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeTypes;
import org.apache.tika.parser.AutoDetectParser;

public class MyFirstTika {

	public static void main(String[] args) throws IOException, TikaException {
		String filename = args[0];
		// Many ways to detect file type
		Tika tika = new Tika();
		String mimeType = tika.detect(new File(filename));
		System.out.println("1.MimeType: " + mimeType);

		AutoDetectParser parser = new AutoDetectParser();
		Detector detector = parser.getDetector();
		System.out.println("2.MimeType: "
				+ detector.detect(new BufferedInputStream(new FileInputStream(new File(filename))), new Metadata()));

		MimeTypes mimeRegistry = TikaConfig.getDefaultConfig().getMimeRepository();

		Detector mimeDetector = (Detector) mimeRegistry;
		System.out.println("3.MimeType: " + mimeDetector
				.detect(new BufferedInputStream(new FileInputStream(new File(filename))), new Metadata()));
	    LanguageDetector langDetector = new OptimaizeLangDetector().loadModels();
	    String text = tika.parseToString(new File(filename));
	    LanguageResult result = langDetector.detect(text);
	    System.out.println("Language: " + result.getLanguage());
		
		

	}

}
