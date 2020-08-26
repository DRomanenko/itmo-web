#include <string>
#include <fstream>

int main () {
    std::string a = "curl \"http://1d3p.wp.codeforces.com/new\" -H \"Connection: keep-alive\" -H \"Cache-Control: max-age=0\" -H \"Origin: http://1d3p.wp.codeforces.com\" -H \"Upgrade-Insecure-Requests: 1\" -H \"DNT: 1\" -H \"Content-Type: application/x-www-form-urlencoded\" -H \"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36\" -H \"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3\" -H \"Referer: http://1d3p.wp.codeforces.com/\" -H \"Accept-Encoding: gzip, deflate\" -H \"Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7\" -H \"Cookie: __utmc=71512449; _gcl_au=1.1.700200108.1568230910; evercookie_etag=p0z4ibcdoddaq5ghuw; evercookie_cache=p0z4ibcdoddaq5ghuw; __utmz=71512449.1568379228.2.2.utmcsr=google^|utmccn=(organic)^|utmcmd=organic^|utmctr=(not^%^20provided); evercookie_png=p0z4ibcdoddaq5ghuw; 70a7c28f3de=p0z4ibcdoddaq5ghuw; _ga=GA1.2.782999320.1568230909; _gid=GA1.2.856472436.1568379243; __utma=71512449.782999320.1568230909.1568379228.1568397104.3; JSESSIONID=06E9EB4F66B28F3243E816C80DAF9E0E; __utmb=71512449.12.10.1568397104\" --data \"_af=34be50b38beccce4^&proof=";
    std::string b = "^&amount=";
    std::string c = "^&submit=Submit\"";
    std::ofstream fout("ans.txt");
    for (int i = 0; i < 100; ++i) {
        fout << a << (i + 1) * (i + 1) << b << i + 1 << c << '\n';
    }
    return 0;
}
