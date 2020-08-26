### Solution:
1. Открываем консоль от имени администратора;
2. Устанавливаем [менеджер пакетов](http://pbox.me/) от Mike Mirzayanov;
3. Устанавливаем [MSYS2](http://pbox.me/packages/msys "Могут не устанавливаться из-за UAC (уменьшить до минимума) и антивируса (отключить)") - cURL и wget в одном (только под Windows), иначе ставим отдельно cURL и wget;
4. Выполняем в консоли: 
```
pkg install curl
pkg install wget
wget --help | grep headers
wget --save-headers http://wp.codeforces.com/1d1p/
cat index.html
curl --header "X-Secret: 6ff6b3" http://wp.codeforces.com/1d1p/
```
5. Получаем ответ: `237482042734`
 
