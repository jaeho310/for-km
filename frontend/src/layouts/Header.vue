<template>
  <v-app-bar dense color="yellow">
    <v-menu>
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon v-bind="attrs" v-on="on" @click="loginCheck">
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          v-for="(item, idx) in items"
          :key="idx"
          @click="clickItem(item)"
        >
          <v-list-item-icon>
            <v-icon v-if="item.icon">{{ item.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-title class="mr-15"> {{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    <v-col cols="11">
      <v-row>
        <v-col cols="4"> 
          <v-app-bar-title class="font-weight-bold">sample-project</v-app-bar-title>
        </v-col>
      </v-row>
    </v-col>
    <v-spacer />
    <v-col>
<!--      <v-btn-->
<!--        v-if="!this.auth"-->
<!--        color="yellow lighten-4"-->
<!--        @click="toLogin"-->
<!--        style="display: inline"-->
<!--      >-->
<!--        로그인-->
<!--      </v-btn>-->
      <v-btn v-if="this.auth" color="yellow lighten-4" @click="logout">
        로그아웃
      </v-btn>
    </v-col>
  </v-app-bar>
</template>

<script>
import { sync } from "vuex-pathify";
import api from "@/utils/api";
import dialog from "@/utils/dialog";

export default {
  name: "DefaultHeader",
  data() {
    return {};
  },
  computed: {
    items: sync("app/items"),
    auth: sync("app/auth"),
    authName: sync("app/authName")
  },
  methods: {
    clickItem(item) {
      this.$router.push(item.to)
      // if (item.to === "/product") {
      //   api({
      //     url: `/product`,
      //     method: "get",
      //   })
      //     .then((res) => {
      //       this.$router.push(res.data)
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     })
      // } else if (item.to === "/order-list") {
      //   console.log('test!!!!!')
      //   api({
      //     url: `/order-list`,
      //     method: "get",
      //   })
      //     .then((res) => {
      //       this.$router.push(res.data)
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     })
      // } else if (item.to === '/') {
      //   this.$router.push('/')
      // }
    },
    // getCookie(key) {
    //   let result = null
    //   let cookie = document.cookie.split(';');
    //   console.log(cookie)
    //   cookie.some(function (item) {
    //     item = item.replace(' ', '');
    //     let cookieItem = item.split('=');
    //     if (key === cookieItem[0]) {
    //       result = cookieItem[1];
    //       return true;
    //     }
    //   });
    //   return result != null;
    // },
    toLogin() {
      this.auth = false
      this.authName = ''
      this.$router.push("/login");
    },
    logout() {
      api({
          url: `/api/members/logout`,
          method: "get",
        })
          .then((res) => {
            this.auth = false
            this.authName = ''
            dialog.makeDialog({text: '로그아웃 되었습니다.', callback: ()=>{this.$router.push('/')}})
          })
          .catch((err) => {
            console.log(err)
          })
    },
    loginCheck() {
    api({
          url: `/api/members/login-check`,
          method: "get",
        })
          .then((res) => {
            if (!res.data.success && res.data.result.message === "로그인이 필요합니다" ) {
              dialog.makeDialog({text: '로그인 페이지로 이동합니다.', callback: this.toLogin})
            } else {
              this.auth = true
            }
          })
          .catch((err) => {
            console.log(err)
          })
  },
  },
  
  /**
   * vuex-pathify 를 사용하여 권한 및 메뉴 관리
   */
  
};
</script>

<style>
ul.menu li {
  float: left;
  padding: 0px 20px;
  list-style: none;
  display: inline;
}
</style>