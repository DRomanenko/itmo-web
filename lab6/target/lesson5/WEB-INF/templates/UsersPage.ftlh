<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user?has_content && user.admin>
                    <th>Admin rights</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr class="data-row">
                    <td class="user_id"></td>
                    <td class="user_login"></td>
                    <#if user?has_content && user.admin>
                        <td class="user_admin">
                            <div class="status"></div>
                            <a class="switch" href="#"></a>
                        </td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    Loading...
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            var $noData = $(".users .noData");

            $.ajax({
                dataType: "json",
                data: {
                    action: "findAll"
                },
                success: function (response) {
                    if (response["users"] === 0) {
                        $noData.find("td").text("No data.");
                        return;
                    }
                    var row = 3;

                    response["users"].forEach(function (user) {
                        $noData.hide();
                        var $tr = $($(".users template").prop("content")).clone();
                        $tr.find(".user_id").text(user["id"]);
                        $tr.find(".user_login").text(user["login"]);
                        if (user["id"] !== ${(user.id)!-1}) {
                            <#if user?has_content && user.admin>
                            if (user["admin"] === true) {
                                $tr.find(".user_admin").find(".switch").text("Disable");
                                $tr.find(".switch").attr("value", "True");
                            } else {
                                $tr.find(".user_admin").find(".switch").text("Enable");
                                $tr.find(".switch").attr("value", "False");
                            }
                            $tr.find(".user_admin").find(".switch").attr("table-row", row);
                            row++;
                            </#if>
                        } else {
                            $tr.find(".user_admin").find(".status").text("You");
                        }
                        $tr.find("tr").attr("data-userId", user["id"]);
                        $tr.find(".switch").attr("data-userId", user["id"]);
                        $(".users tbody").append($tr);
                    });


                    $("a.switch").on('click', function () {
                        // $("a.switch").text("DEMIAN");
                        <#if user.admin>
                        var value = $(this).attr("value") === "True" ? 0 : 1;

                        $.ajax({
                            dataType: "json",
                            data: {
                                action: "setAdmin",
                                id: $(this).attr("data-userId"),
                                value: value
                            },
                            success: function () {
                                if (value === 1) {
                                    $(this).text("Disable");
                                    $(this).attr("value", "True");
                                } else {
                                    $(this).text("Enable");
                                    $(this).attr("value", "False");
                                }
                            },
                            type: "POST"
                        });
                        if (value === 1) {
                            $(this).text("Disable");
                            $(this).attr("value", "True");
                        } else {
                            $(this).text("Enable");
                            $(this).attr("value", "False");
                        }
                        </#if>
                        // return false;
                    });

                    $(".users tr").click(function () {
                        $.ajax({
                            dataType: "json",
                            data: {
                                action: "findUser",
                                userId: $(this).attr("data-userId")
                            },
                            success: function (response) {
                                notify(response["user"].creationTime);
                            },
                            type: "POST"
                        })
                    });
                },
                type: "POST"
            });
        })
    </script>
</@c.page>
