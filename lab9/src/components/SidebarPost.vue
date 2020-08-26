<template>
    <section>
        <div class="header">
            By {{users[post.userId].login}}
        </div>
        <div class="body">
            {{post.text}}
        </div>
        <div class="footer">
            <a href="#page=PostWithComments" @click="showPostComments()">View all</a>
        </div>
    </section>
</template>

<script>
    export default {
        props: ['users', 'post'],
        name: "SidebarPost",
        methods: {
            showPostComments: function () {
                this.$root.$emit("onShowPostComments", this.post);
            }
        }
    }
</script>

<style scoped>

</style>
