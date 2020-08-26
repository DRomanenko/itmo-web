package ru.itmo.tpl.util;

import ru.itmo.tpl.model.Color;
import ru.itmo.tpl.model.MenuElement;
import ru.itmo.tpl.model.Post;
import ru.itmo.tpl.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mikhail Mirzayanov", Color.RED),
            new User(2, "tourist", "Genady Korotkevich", Color.RED),
            new User(3, "emusk", "Elon Musk", Color.GREEN),
            new User(5, "pashka", "Pavel Mavrin", Color.RED),
            new User(7, "geranazavr555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "cannon147", "Erofey Bashunov", Color.GREEN)
    );

    /* Start 2 */
    private static final List<MenuElement> MENU = Arrays.asList(
            new MenuElement("Index", "/index"),
            new MenuElement("Help", "/misc/help"),
            new MenuElement("Users", "/users")
    );
    /* End 2 */

    /* Start 4 */
    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "Разнообразный и богатый опыт сложившаяся",
                    "Соображения высшего порядка, а также выбранный нами инновационный " +
                            "путь способствует подготовке и реализации всесторонне сбалансированных нововведений.\n" +
                            "\n" +
                            "С другой стороны реализация...", 7),
            new Post(2, "Повседневная практика показывает, что консультация", "Равным образом постоянный количественный рост и " +
                    "сфера нашей активности позволяет оценить значение системы масштабного изменения ряда параметров! " +
                    "Значимость этих проблем настолько очевидна, что дальнейшее развитие различных форм деятельности требует определения...", 11),
            new Post(3, "End", "Последний пост", 1),
            new Post(100000, "dsada", "dasdasd", 1)
    );
    /* End 4 */

    private static List<User> getUsers() {
        return USERS;
    }

    private static List<MenuElement> getMenu() {
        return MENU;
    }

    private static List<Post> getPosts() {
        return POSTS;
    }

    public static void putData(Map<String, Object> data) {
        data.put("users", getUsers());
        /* Start 2 */
        data.put("menu", getMenu());
        /* End 2 */
        /* Start 4 */
        data.put("posts", getPosts());
        /* End 4 */
        for (User user : getUsers()) {
            Long boii = user.getId();
            if (boii.equals(data.get("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}