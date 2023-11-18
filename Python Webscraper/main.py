import dataManager
import scrape
import os.path

def main():
    go = 1
    while go == 1:
        print("1) enterData\n2) removeAllData\n3) printTable\n4) QUIT")
        choice = int(input("Please make a selection: "))
        match choice:
            case 1:
                urlToScrape = input("Enter URL to scrape: ")
                dm = dataManager
                dm.enterData(scrape.aquireData(urlToScrape))
            case 2:
                if os.path.isfile('./countries.db'):
                    dm = dataManager
                    dm.deleteAllData
                    print("Deleted all rows from table.")
                else:
                    print("There is no current database.")
            case 3:
                if os.path.isfile('./countries.db'):
                    dm = dataManager
                    dm.printTable
                else:
                    print("There is no current database.")
            case 4:
                go = 2
            case _:
                go = 3

if __name__ == "__main__":
    main()