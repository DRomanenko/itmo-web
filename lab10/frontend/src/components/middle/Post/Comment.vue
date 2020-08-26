<template>
    <div class="comment">
        <div class="title">Comments:</div>
        <div class="author">
            <div class="left">
                From:
            </div>
            <div class="right">
                {{comment.user.login}}
            </div>
        </div>
        <div class="body">
            {{comment.text}}
        </div>
    </div>
</template>

<script>
    export default {
        props: ['users', 'comment'],
        name: "Comment",
        beforeCreate() {
            this.$root.$on("onChangePage", (page) => {
                this.page = page;
            });
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

    .comment {
        border: 1px solid var(--border-color);
        border-radius: var(--border-radius);
        overflow: hidden;
        padding: 0.2rem;
        margin-top: 0.5rem;
    }

    .left {
        display: inline;
        margin: 0.2rem 0;
        font-size: 1rem;
        color: var(--caption-color);
    }

    .right {
        display: inline;
    }
</style>