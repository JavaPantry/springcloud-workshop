<template>
    <v-layout>
        <Nav />
        <v-main>
            <v-container fluid>
            <slot />
            </v-container>
        </v-main>
    </v-layout>
</template>

<style scoped>
.router-link-exact-active {
    color: #12b488;
}
</style>