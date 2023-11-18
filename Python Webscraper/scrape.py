import requests
from bs4 import BeautifulSoup

def aquireData(urlAddress):
    #https://www.scrapethissite.com/pages/simple/
    url = urlAddress
    response = requests.get(url)
    html = response.text
    soup = BeautifulSoup(html, 'html.parser')
    
    #Define Lists
    country_names = []
    capital_names = []
    pop_nums = []
    area_nums = []
    
    #Add all data to lists
    for i in soup.find_all('h3', class_='country-name'):
        country_names.append(i.text.strip())
    for i in soup.find_all('span', class_='country-capital'):
        capital_names.append(i.text.strip())
    for i in soup.find_all('span', class_='country-population'):
        pop_nums.append(i.text.strip())
    for i in soup.find_all('span', class_='country-area'):
        area_nums.append(i.text.strip())
        
    #Combine lists into Tuples
    country_data = []
    for (country_name, capital_name, pop, area) in zip(country_names, capital_names, pop_nums, area_nums):
        country_data.append((country_name, capital_name, pop, area))
    
    return country_data

