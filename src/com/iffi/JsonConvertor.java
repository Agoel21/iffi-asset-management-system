package com.iffi;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * A class to output the given objects into json files
 */

public class JsonConvertor {
	public static void PersonJson(List<Person> persons) {

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();

		File f = new File("data/Persons.json");
		try {

			PrintWriter pw = new PrintWriter(f);
			String json = gson.toJson(persons);
			pw.write(json);

			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void AssetJson(List<Asset> loadAssets) {

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();

		File f = new File("data/Assets.json");
		try {

			PrintWriter pw = new PrintWriter(f);
			String json = gson.toJson(loadAssets);
			pw.write(json);

			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
