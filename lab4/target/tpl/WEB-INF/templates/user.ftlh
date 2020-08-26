<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="Long" -->
<#import "common.ftl" as c/>

<#import "common.ftl" as c/>

<#macro body viewedUser viewedUserIndex>
    <#if viewedUser.id??>
        <div class="user_profile">
            <div class="username"><@c.userlink_handle user=viewedUser nameOnly=false /></div>
            <ul>
                <li>id: ${viewedUser.id?c}</li>
                <li>handle: ${viewedUser.handle}</li>
                <li>name: ${viewedUser.name}</li>
                <li>
                    <a href="/posts?user_id=${viewedUser.id}">Posts: ${c.findByCount(posts, "user_id", viewedUser.id)}</a>
                </li>
            </ul>
            <img src="../../img/user.jpg"/>
        </div>
        <div class="other_users">
            <#if viewedUserIndex != 0><a href="/user?handle=${users[viewedUserIndex-1].handle}">←</a>
            <#else> <a class="inactive">←</a>
            </#if>
            <#if viewedUserIndex != (users?size-1) ><a href="/user?handle=${users[viewedUserIndex+1].handle}">→</a>
            <#else> <a class="inactive">→</a>
            </#if>
        </div>
    <#else>
        <h1>No such user</h1>
    </#if>
</#macro>

<@c.page>
    <#if handle??>
        <#assign viewedUser=c.findBy(users, "handle", handle)!/>
        <#assign viewedUserIndex=c.findByIndex(users, "handle", handle)!/>
        <@body viewedUser=viewedUser viewedUserIndex=viewedUserIndex/>
    <#elseif user_id??>
        <#assign viewedUser=c.findBy(users, "id", user_id)!/>
        <#assign viewedUserIndex=c.findByIndex(users, "id", user_id)!/>
        <@body viewedUser=viewedUser viewedUserIndex=viewedUserIndex/>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>
