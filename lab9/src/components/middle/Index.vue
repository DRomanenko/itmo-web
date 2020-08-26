<template>
    <div class="datalist articles">
        <Post v-for="post in reversePosts" :users="users" :post="post" :key="post.id" :comments="comments"/>
    </div>
</template>

<script>
    import Post from './Post';
    export default {
        props: ["users", "posts", "comments"],
        name: "Index",
        components: {
            Post: Post
        },
        computed: {
            reversePosts: function () {
                return Object.values(this.posts).slice().reverse()
            }
        }
    }
</script>

<style scoped>
</style>