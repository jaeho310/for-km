<template>
  <v-app-bar dense color="yellow">
    <v-menu>
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon v-bind="attrs" v-on="on" @click="isLogin">
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item v-for="(item, idx) in items" :key="idx" @click="clickItem(item)">
          <v-list-item-icon>
            <v-icon v-if="item.icon">{{ item.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-title class="mr-15"> {{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    <v-col cols="11">
      <v-app-bar-title class="font-weight-bold">sample-project</v-app-bar-title>
    </v-col>
    <v-spacer/>
    <v-col>
        <v-btn v-if="!this.auth" color="yellow lighten-4" @click="toLogin" style="display: inline;">
          로그인
        </v-btn>
        <v-btn v-if="this.auth" color="yellow lighten-4" @click="toLogout">
          로그아웃
        </v-btn>
    </v-col>
  </v-app-bar>
</template>

<script>
import {sync} from 'vuex-pathify'

export default {
  name: "DefaultHeader",
  data() {
    return {}
  },
  methods: {
    clickItem(item) {
      console.log(item.to)
      this.$router.push(item.to)
    },
    getCookie(key) {
      let result = null
      let cookie = document.cookie.split(';');
      console.log(cookie)
      cookie.some(function (item) {
        item = item.replace(' ', '');
        let cookieItem = item.split('=');
        if (key === cookieItem[0]) {
          result = cookieItem[1];
          return true;
        }
      });
      return result != null;
    },
    /**
     * 메뉴를 클릭할때 JSESSIONID를 확인하여
     * 서버에서 발급한 세션키를 쿠키로 가지고있는지 확인한다.
     */
    isLogin() {
      // JSESSIONID 라는 쿠키 확인
      if (this.getCookie('JSESSIONID')) {
        // 로그인하면 쿠키가 발급되므로 로그아웃메뉴만 보여준다.
        this.auth = true
      } else {
        this.auth = false
      }
      this.toLogin()
    },
    toLogin() {
      this.$router.push("/login")
    },
    toLogout() {
      this.auth = false
    }
  },
  /**
   * vuex-pathify 를 사용하여 권한 및 메뉴 관리
   */
  computed: {
    items: sync('app/items'),
    auth: sync('app/auth'),
  },
  mounted() {
    // this.isLogin()
  }
}
</script>

<style>
ul.menu li {
  float: left;
  padding: 0px 20px;
  list-style: none;
  display: inline;
}
</style>