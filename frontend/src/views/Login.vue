<template>
  <v-card max-width="350" class="mx-auto mt-15">
    <v-card-title>
      <div class="font-weight-bold text-h5 black--text">
        로그인
      </div>
    </v-card-title>
    <v-card-text class="mt-2">
      <v-text-field
          label="id"
          v-model="id"
          outlined
          dense>
      </v-text-field>
      <v-text-field
          type="password"
          label="password"
          v-model="password"
          outlined
          dense>
      </v-text-field>
    </v-card-text>
    <v-card-actions>
      <v-spacer/>
      <v-btn
          text
          color="blue accent-4"
          @click="toJoin"
      > 회원가입
      </v-btn>
      <v-btn
          text
          color="teal accent-4"
          @click="login"
      > 로그인
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import dialog from "@/utils/dialog";
import api from "@/utils/api";
import { sync } from "vuex-pathify"

export default {
  name: "login",
  data() {
    return {
      id: '',
      password: '',
    }
  },
  methods: {
    promiseRejectHandler(err) {
      dialog.makeDialog({text: "시스템 에러가 발생했습니다."})
      console.log(err)
    },
    login() {
      if (this.id === '' || this.password === '') {
        dialog.makeDialog({text: '아이디와 비밀번호를 모두 입력해주세요'})
        return
      }
      this.loginRequest(this.id, this.password)
    },
    loginResponseHandler(res) {
      return new Promise((resolve, reject) => {
        try {
          if (res.data.success) {
            console.log(document.cookie)
            console.log('test!!')
            dialog.makeDialog({text: `로그인되었습니다.`, callback: this.toHome})
          } else {
            console.log(res.data.result.message)
            dialog.makeDialog({text: `로그인에 실패했습니다.`})
          }
          resolve()
        } catch (error) {
          reject(error)
        }
      })
    },
    loginRequest(id, password) {
      api({
        url: "/api/members/login",
        method: "post",
        data: {
          'memberId': this.id,
          'password': this.password
        }
      })
        .then(this.loginResponseHandler)
        .catch(this.promiseRejectHandler)
    },
    toJoin() {
      this.$router.push("/join")
    },
    toHome() {
      this.$router.push("/")
    }
  }
}
</script>