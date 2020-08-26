### Solution:
1. Ввожу одно число, в Chrome DevTools во вкладке `Newtwork` появляется запрос `new`;
2. Правой кнопкой мыши по запросу `-> Copy -> Copy all as cURL`;
3. Убираю `--compressed --insecure` из скаченного [cURL](./source/curl.text); 
4. Заметив особенность `proof=144^&amount=12` - proof зависит от amount, [генерирую 100 запросов](./source/gen.cpp);
5. Получаю нужный набор [cURLs](./source/ans.txt). 
