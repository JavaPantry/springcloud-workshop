<template>
    <div>
        <Nav />
        <div class="container mx-auto p-4">
            <slot />
        </div>
    </div>
</template>

<style scoped>
.router-link-exact-active {
    color: #12b488;
}
</style>