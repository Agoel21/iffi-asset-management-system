# IFFI Asset Management System

A command-line tool that reads client accounts, their assets, and the people
tied to them from CSV files, then prints a fee/return/value report — modeling
stocks, options, real estate, and crypto with the actual cost-basis and
return math for each asset type.

## What it does

An account holds a mix of assets — stocks, calls, puts, property, crypto —
each with its own rules for cost basis, current value, gain, and fee. The
program loads that data from CSVs, computes the numbers per asset and rolls
them up per account, and prints a summary table (sorted by owner) plus a
detailed per-account breakdown to stdout.

It's a two-person academic project (Akshita Goel and Lindsey Wiegert),
written in plain Java with no framework or database — just object modeling,
manual CSV parsing, and one JSON export helper.

## Requirements

- JDK 17+ (tested on JDK 17)
- No build tool required — everything is compiled by hand with `javac`

## Running it

From the repo root:

```bash
mkdir -p bin
javac -d bin -cp lib/gson-2.9.0.jar $(find src -name "*.java")
java -cp bin:lib/gson-2.9.0.jar com.iffi.AccountReport
```

This reads `data/Persons.csv`, `data/Assets.csv`, and `data/Accounts.csv`
(sample data is checked in) and prints the report to stdout. Expected output
is checked in at `data/output.txt` for comparison.

Sample output:

```
Account Summary Report By Owner

Account   Owner              Manager             Fees      Return          Ret%       Value
AC3       O'Niel, Timothy    Todd, Bruno         $0.000    $1835410.500    1444.636   $1962460.500
AC2       Todd, Bruno        Yetti, Harper       $0.000    $173248.160     516.574    $206786.060
AC1       Yetti, Harper      O'Niel, Timothy     $82.500   $851.395        26.731     $4036.395

                    Overall Totals:    $82.50    2009510.06         $2173282.96
```

To use your own data, edit the CSVs in `data/` following the existing rows —
the first line of each file is a row count that must match the number of
data rows that follow.

## Design notes

- `Asset` is the abstract base for `Stock`, `Property`, and `Crypto`, each
  with its own cost basis / value / gain / return formulas. `Option`
  (abstract) extends `Stock` and is the base for `Call` and `Put`.
- `Account` aggregates a list of assets and computes totals; "Pro" accounts
  (type `P`) get a 25% discount on total fees, "Noob" accounts (type `N`)
  pay full fees.
- `FileDataLoader` does the CSV parsing; `JsonConvertor` is a one-way export
  helper (objects → `data/Persons.json` / `data/Assets.json`) that isn't
  wired into the CSV-reading path — it's a separate export utility, not a
  second input format.

## Limitations

- CSV parsing is hand-rolled (`Scanner` + `split(",")`) with no validation —
  a malformed row, a missing person code, or a wrong row count in the first
  line will throw rather than fail gracefully.
- No persistence beyond flat files: no database, no way to add/edit accounts
  except by hand-editing the CSVs.
- `data/Persons.json` / `data/Assets.json` are stale sample exports, not
  live data the program reads back in.
- No automated tests.
- Data in `data/` is entirely fictional (`Yetti`, `O'Niel`, `Popper`, sample
  emails), included only to make the program runnable out of the box.

---

Akshita Goel (@akgoel) and Lindsey Wiegert (@lwiegert)
