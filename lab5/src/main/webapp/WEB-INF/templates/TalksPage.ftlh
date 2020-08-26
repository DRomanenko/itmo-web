<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.service.TalkService.Chat[]" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="targetUserLogin" type="java.lang.String" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="talks-box">
        <div class="header">Write a message</div>
        <div class="body">
            <form method="post" action="/talks">
                <input type="hidden" name="action" value="addTalk">
                <div class="field">
                    <div class="name">
                        <label for="targetUserLogin">Login of recipient:</label>
                    </div>
                    <div class="value">
                        <select name="targetUserLogin">
                            <#list users as user>
                                <option value="${user.login}">${user.login}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text of message:</label>
                    </div>
                    <div class="text-value">
                        <textarea id="text" name="text" rows="5">${text!}</textarea>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
            </form>
        </div>
    </div>

    <#if talks?size==0>
        <div class="empty">
            You haven't chat with anyone yet
        </div>
    <#else>
        <div class="datatable">
            <div class="caption">Chat</div>
            <table>
                <thead>
                <tr>
                    <th>id</th>
                    <th>from</th>
                    <th>to</th>
                    <th>message</th>
                    <th>creationTime</th>
                </tr>
                </thead>
                <tbody>
                <#list talks as talk>
                    <tr>
                        <td>${talk.id}</td>
                        <td>${talk.sourceUser.login}</td>
                        <td>${talk.targetUser.login}</td>
                        <td>${talk.text}</td>
                        <td>${talk.creationTime}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
</@c.page>
