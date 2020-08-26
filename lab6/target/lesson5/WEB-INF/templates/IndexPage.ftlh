<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <template>
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="body"></div>
                <div class="footer">
                    <div class="left">
                        <img src="../../img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">0</span>
                        <img src="../../img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="../../img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        <div class="date"></div>
                        <img src="../../img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">0</a>
                    </div>
                </div>
            </article>
        </template>

        <div class="noData">
            Loading...
        </div>
    </div>

    <script>
        $(function () {
            var $noData = $(".articles .noData");

            $.ajax({
                dataType: "json",
                data: {
                    action: "findAll"
                },
                success: function(response) {
                    if (response["articles"].length === 0) {
                        $noData.text("No data.");
                        return;
                    }
                    response["articles"].forEach(function(article) {
                        $noData.hide();

                        $.ajax({
                            dataType: "json",
                            data: {
                                action: "findUser",
                                id: article["userId"]
                            },
                            success: function(response) {
                                var userLogin = response["requested-user"]["login"];
                                var $article = $($(".articles template").prop("content")).clone();
                                $article.find(".title").text(article["title"]);
                                $article.find(".information").text("By user " + userLogin + ", " + article["creationTime"]);
                                $article.find(".body").text(article["text"]);
                                $article.find(".footer .right .date") .text(article["creationTime"]);
                                $(".articles").append($article);
                            },
                            type: "GET"
                        });
                    });
                },
                type: "POST"
            });
        })
    </script>

    <script>
        $(function () {
            $("aside section").click(function () {
                $(this).css("background-color", "red");
            });
        })
    </script>
</@c.page>
