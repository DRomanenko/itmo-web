<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring>
<#-- @ftlvariable name="notice" type="ru.itmo.wp.domain.Notice" -->

<@c.page>
    <div class="notice form-box">
        <div class="header">Create Notice</div>
        <form method="post" action="">
            <input type="hidden" name="action" value="addNotice"/>
            <div class="field">
                <div class="name">
                    <label for="content">Text</label>
                </div>
                <div class="value">
                    <label>
                        <textarea id="content" name="content">${notice.content!}</textarea>
                    </label>
                </div>
                <@c.error "notice.content"/>
            </div>

            <div class="button-field">
                <input type="submit" value="Create">
            </div>
        </form>
    </div>
</@c.page>