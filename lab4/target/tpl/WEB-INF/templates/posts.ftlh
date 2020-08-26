<#-- @ftlvariable name="user_id" type="Long" -->
<#import "common.ftl" as c/>

<#import "common.ftl" as c/>

<@c.page>
    <#if user_id??>
        <#assign viewedUser=c.findBy(users, "id", user_id)!/>
        <#if viewedUser.id??>
            <div class="middle">
                <ul class="posts_line">
                    <#assign cnt=0>
                    <#list posts as p>
                        <#if p["user_id"]==user_id>
                            <li><@c.createPost post=p shouldShorten=true /></li>
                            <#assign cnt=cnt+1>
                        </#if>
                    </#list>
                </ul>
            </div>
            <#if cnt==0>
                <h1>Have no posts yet</h1>
            </#if>
        <#else>
            <h1>User not found</h1>
        </#if>
    <#else>
        <h1>User not found</h1>
    </#if>
</@c.page>

