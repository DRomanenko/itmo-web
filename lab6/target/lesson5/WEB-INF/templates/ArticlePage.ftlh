<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="submit-article large-form-box">
        <div class="header">Create new article</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="submit"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title" value=""/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text"></textarea>
                    </div>
                </div>
                <div class="error">
                    ${error!}
                </div>
                <div class="button-field">
                    <input type="submit" value="Submit">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".submit-article form").submit(function () {
                var title = $(this).find("input[name='title']").val();
                var text = $(this).find("textarea[name='text']").val();
                var error = $(this).find(".error");

                ajax({
                    data: {
                        action: "submit",
                        title: title,
                        text: text
                    }
                }, error);
                return false;
            });
        })
    </script>
</@c.page>