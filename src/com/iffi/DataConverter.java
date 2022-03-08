package com.iffi;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * A data class to convert csv files 
 * and store the contents into the respective classes
 */

public class DataConverter {

	private static final String fPersons = "data/Persons.csv";
	private static final String fAssets = "data/Assets.csv";
	private static final String fAccounts = "data/Accounts.csv";

	/*
	 * This method uses a scanner to to read the person.csv file and then tokenize
	 * it to store in respective classes
	 */
	public static List<Person> loadPerson() {
		List<Person> persons = new ArrayList<Person>();

		Scanner s = null;
		try {
			s = new Scanner(new File(fPersons));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		String lineOne = s.nextLine();
		int counter = Integer.parseInt(lineOne);

		for (int i = 0; i < counter; i++) {
			String line = s.nextLine();
			String tokens[] = line.split(",", -1);
			String code = tokens[0];
			String lastName = tokens[1];
			String firstName = tokens[2];
			Address address = new Address(tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
			List<String> email = new ArrayList<String>();// for multiple email
			if (tokens.length > 8) {
				for (int j = 8; j < tokens.length; j++) {
					email.add(tokens[j]);
				}
			}
			Person p = new Person(code, lastName, firstName, address, email);
			persons.add(p);

		}
		s.close();
		return persons;

	}

	/*
	 * This method uses a scanner to to read the assets.csv file and then tokenize
	 * it to store in respective classes
	 */
	public static List<Asset> loadAssets() {
		List<Asset> asset = new ArrayList<Asset>();

		Scanner s = null;
		try {
			s = new Scanner(new File(fAssets));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		String lineOne = s.nextLine();
		int counter1 = Integer.parseInt(lineOne);
		for (int i = 0; i < counter1; i++) {
			String line = s.nextLine();
			if (!line.trim().isEmpty()) {
				Asset a = null;
				String tokens[] = line.split(",");
				String code = tokens[0];
				String type = tokens[1];
				String label = tokens[2];
				double appraisedValue = 0.0;
				double sharePrice = 0.0;
				double exchangeRate = 0.0;
				double exchangeFeeRate = 0.0;
				if (tokens[1].equals("P")) {
					appraisedValue = Double.parseDouble(tokens[3]);
					a = new Property(code, type, label, appraisedValue);
				} else if (tokens[1].equals("S")) {
					String symbol = tokens[3];
					sharePrice = Double.parseDouble(tokens[4]);
					a = new Stock(code, type, label, symbol, sharePrice);
				} else if (tokens[1].equals("C")) {
					exchangeRate = Double.parseDouble(tokens[3]);
					exchangeFeeRate = Double.parseDouble(tokens[4]);
					a = new Crypto(code, type, label, exchangeRate, exchangeFeeRate);
				}

				asset.add(a);

			}

		}

		return asset;
	}

	/**
	 * this method takes a CSV file and tokenizes the content and stores it in the
	 * respectice classes
	 * 
	 * @param assetList
	 * @return
	 */
	public static List<Account> loadAccount(List<Asset> assetList) {
		List<Account> accounts = new ArrayList<Account>();
		List<Person> persons = loadPerson();
		Map<String, Person> personMap = new HashMap<>();
		for (Person person : persons) {
			personMap.put(person.getCode(), person);
		}

		// call assetlist function
		Scanner s = null;
		try {
			s = new Scanner(new File(fAccounts));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		String lineOne = s.nextLine();
		int counter = Integer.parseInt(lineOne);

		for (int i = 0; i < counter; i++) { // this is the account lines
			String line = s.nextLine();
			String tokens[] = line.split(",");
			String accountNumber = tokens[0];
			String type = tokens[1];
			Person owner = personMap.get(tokens[2]);
			Person manager = personMap.get(tokens[3]);
//			Person beneficiary = null;
//			if(tokens[4]=="") {
//			 return Person beneficiary ;
//			}else {
			Person beneficiary = personMap.get(tokens[4]);
			List<Asset> accountAssets = new ArrayList<Asset>();
			// Account k = new Account(accountNumber, type, ownerCode, managerCode,
			// beneficiaryCode);
			for (int j = 5; j < tokens.length; j++) { // Account info (assets)
				String acode = null;
				for (Asset a : assetList) {
					String atype = a.getType();
					acode = a.getCode();
					if (j < tokens.length) {
						if (tokens[j].equals(acode)) {

							if (atype.equals("S")) {
								String symbol = tokens[j + 1];
								if (symbol.equals("S")) {
									Stock stock = new Stock((Stock) a, LocalDate.parse(tokens[j + 2]),
											Double.parseDouble(tokens[j + 3]), Double.parseDouble(tokens[j + 4]),
											Double.parseDouble(tokens[j + 5]));
									accountAssets.add(stock);
									j += 5;
									break;

								} else if (symbol.equals("C")) {

									Call c = new Call((Stock) a, LocalDate.parse(tokens[j + 2]),
											Double.parseDouble(tokens[j + 3]), Double.parseDouble(tokens[j + 4]),
											Double.parseDouble(tokens[j + 5]), LocalDate.parse(tokens[j + 6]));
									accountAssets.add(c);
									j += 6;
									break;
								} else if (symbol.equals("P")) {
									Put p = new Put((Stock) a, LocalDate.parse(tokens[j + 2]),
											Double.parseDouble(tokens[j + 3]), Double.parseDouble(tokens[j + 4]),
											Double.parseDouble(tokens[j + 5]), LocalDate.parse(tokens[j + 6]));
									accountAssets.add(p);
									j += 6;
									break;
								}
							}

							else if (atype.equals("P")) {
								Property p = new Property((Property) a, LocalDate.parse(tokens[j + 1]),
										Double.parseDouble(tokens[j + 2]));// )
								accountAssets.add(p);
								j += 2;
								break;

							} else if (atype.equals("C")) {
								Crypto c = new Crypto((Crypto) a, LocalDate.parse(tokens[j + 1]),
										Double.parseDouble(tokens[j + 2]), Double.parseDouble(tokens[j + 3]));
								accountAssets.add(c);
								j += 3;
								break;
							}

						}
					}
				}

			}
			Account k = new Account(accountNumber, type, owner, manager, beneficiary, accountAssets);
			accounts.add(k);

		}
		s.close();
		return accounts;

	}

}
