<template>
  <v-card xs12 sm4 md4 lg4 max-width="220" min-height="215" class="elevation-5 ml-4">
    <v-card-title>
    <router-link :to="link">
      <v-img class="mt-6 mb-0" :src="sectionImg" alt="logo"></v-img>
    </router-link>
    </v-card-title>
    <v-card-actions>
      <v-spacer/>
      <h4 style="text-align: center">
        <router-link :to="link"> {{sectionDescription}} </router-link>
      </h4>
      <v-spacer/>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "MenuCard",
  props: {
    sectionDescription: {
      type: String,
      default: function() {
        return "";
      }
    },
    sectionImg: {
      type: String,
      default: function() {
        return "";
      }
    },
    link: {
      type: String,
      default: function() {
        return "";
      }
    },
  }
}
</script>

<style lang="scss" scoped>
.v-card-actions a {
  text-decoration: none;
}
</style>

