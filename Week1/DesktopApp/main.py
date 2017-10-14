from urllib.parse import urlencode
from urllib.request import Request, urlopen
import concurrent.futures
import random
import string

url = "http://localhost:8080/word"


def get_words(post_data):
    request = Request(url, urlencode(post_data).encode(), headers={"Accept": "application/json"})
    response = urlopen(request).read().decode()
    print(response)


if __name__ == "__main__":
    with concurrent.futures.ThreadPoolExecutor(max_workers=100) as executor:
        data = []
        for i in range(0, 1000):
            key = ''.join(random.choice(string.ascii_letters + string.digits) for _ in range(6))
            value = ''.join(random.choice(string.ascii_letters + string.digits) for _ in range(6))
            data.append({'key': key, 'value': value})

        futures = [executor.submit(get_words, post_data) for post_data in data]
        for future in concurrent.futures.as_completed(futures):
            try:
                response = future.result()
                print(response)
            except Exception as ex:
                print(ex)
