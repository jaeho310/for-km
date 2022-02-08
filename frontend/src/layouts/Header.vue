<template>
  <v-app>
    <v-card
      color="orange"
      flat
    >
      <v-app-bar
        color="blue lighten-1"
        dark
        dense
      >
        <v-app-bar-nav-icon @click="drawer = !drawer" />
        <v-toolbar-title>Platform Backoffice</v-toolbar-title>
        <v-spacer />
      </v-app-bar>
    </v-card>
    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
      color="blue lighten-5"
    >
      <v-list-item>
        <v-list-item-avatar>
          <v-img src="https://t1.daumcdn.net/cfile/tistory/2459514E550A64DD27"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>Platform Backoffice</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider />

      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
          :to="item.to"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <router-view />
  </v-app>
</template>

<script>
import { sync } from 'vuex-pathify'
export default {
  name: "DefaultHeader",
  data() {
    return {
      drawer: true,
    }
  },
  computed: {
    items: sync('app/items'),
  },
}
</script>

<style>
  ul.menu li{float:left; padding:0px 20px; list-style:none; display: inline;}
</style>