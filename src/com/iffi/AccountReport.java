package com.iffi;

import java.util.Collections;
import java.util.List;

/**
 * a class that produces a summary and account report using DataConverter
 * 
 * @author akshitagoel
 * @author lindseyWiegert Date : 2022/03/06
 *
 */

public class AccountReport {

	public static void main(String args[]) {

		System.out.println("Account Summary Report By Owner\n");
		System.out.println("==================================================================================="
				+ "============================================================================\n");
		System.out.println("Account\t\tOwner\t\t\t Manager\t\t\t Fees\t\t Return\t\t\t Ret%\t\t Value\n   ");
		List<Asset> b = FileDataLoader.loadAssets();
		List<Account> a = FileDataLoader.loadAccount(b);

		double summaryTotalValue = 0;
		double summaryTotalReturn = 0;
		double summaryTotalFees = 0;
		Collections.sort(a);
		for (Account s : a) {
			summaryTotalValue += s.getValueTotal();
			summaryTotalFees += s.getFinalFees();
			summaryTotalReturn += s.getTotalReturn();

			System.out.printf("%-8s  %s, %-20s %s, %-25s  $%-15.3f  $%-15.3f    %-15.3f $%.3f \n ",
					s.getAccountNumber(), s.getOwner().getLastName(), s.getOwner().getFirstName(),
					s.getManager().getLastName(), s.getManager().getFirstName(), s.getFinalFees(), s.getTotalReturn(),
					s.getTotalPercentage(), s.getValueTotal());

		}
		System.out.printf("\n");
		System.out.printf("\t\t\t\t\t Overall Totals:\t   \t $%.2f   \t %.2f \t \t\t      $%.2f\n\n", summaryTotalFees,
				summaryTotalReturn, summaryTotalValue);

		System.out.println("=========================================================================================="
				+ "=======================================================================\n");
		System.out.printf("\n\n");
		System.out.println("ACCOUNT DETAILS\n");
		for (Account acc : a) {
			System.out.println(acc);
			System.out.printf("TOTALS\n");
			System.out.printf("Total Value:\t\t $%.3f\n", acc.getValueTotal());
			System.out.printf("Cost Basis:\t\t $%.3f\n", acc.getTotalCost());
			System.out.printf("Total Account Fees:\t $%.3f\n", acc.getFinalFees());
			System.out.printf("Total Return:\t\t $%.3f\n", acc.getTotalReturn());
			System.out.printf("Total Return%%:\t\t $%.3f\n\n", acc.getTotalPercentage());
		}

	}
}
