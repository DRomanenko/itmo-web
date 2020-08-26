<template>
    <div class="article">
        <Post :users="users" :post="post" :comments="comments"/>
        <div class="title">Comments:</div>
        <div v-if="postComments.length > 0" class="comments">
            <section v-for="comment in postComments" :key="comment.id">
                <div class="author">
                    <div class="left">
                        From:
                    </div>
                    <div class="right">
                        {{users[comment.userId].login}}
                    </div>
                </div>
                <div class="body">
                    {{comment.text}}
                </div>
            </section>
        </div>
        <div v-else class="no-comments">
            No comments
        </div>
    </div>
</template>

<script>
    import Post from './Post';

    export default {
        props: ['users', 'post', 'comments'],
        name: "PostWithComments",
        components: {
            Post: Post
        },
        computed: {
            postComments: function () {
                return Object.values(this.comments).filter(a => a.postId === this.post.id);
            }
        }
    }
</script>

<style scoped>
    .title {
        color: var(--caption-color);
        font-weight: bold;
        font-size: 1rem;
        text-decoration: none;
    }

    .comments section {
        border: 1px solid var(--border-color);
        border-radius: var(--border-radius);
        overflow: hidden;
        padding: 0.2rem;
        margin-top: 0.5rem;
    }

    .comments section .left {
        display: inline;
        margin: 0.2rem 0;
        font-size: 1rem;
        color: var(--caption-color);
    }

    .comments section .right {
        display: inline;
    }

    .comments section .no-comments {
        color: var(--caption-color);
        font-weight: bold;
        font-size: 1rem;
    }
</style>