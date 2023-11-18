import sqlite3

def enterData(data):
    conn = sqlite3.connect('countries.db')
    cur = conn.cursor()
    cur.execute('''CREATE TABLE IF NOT EXISTS countries (country_name TEXT, capital_name TEXT, population TEXT, area TEXT)''')
    cur.executemany('INSERT INTO countries (country_name, capital_name, population, area) VALUES (?, ?, ?, ?)', data)
    conn.commit()
    cur.close()
    conn.close()
    
def deleteAllData():
    conn = sqlite3.connect('countries.db')
    cur = conn.cursor()
    with conn:
        cur.execute('DELETE FROM countries;',)
    cur.close()
    conn.close()
    
def printTable():
    conn = sqlite3.connect('countries.db')
    cur = conn.cursor()
    with conn:
        cur.execute('SELECT * FROM countries')
        print(cur.fetchall())
    cur.close()
    conn.close()
