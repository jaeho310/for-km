<template>
  <v-card max-width="350" class="mx-auto mt-15">
    <v-card-title>
      <div class="font-weight-bold text-h5 black--text">
        회원가입
      </div>
    </v-card-title>
    <v-card-text class="mt-2">
      <v-text-field
          label="id"
          v-model="id"
          outlined
          @click="isDuplicated=true"
          dense>
      </v-text-field>
      <v-text-field
          label="password"
          v-model="password"
          outlined
          dense>
      </v-text-field>
      <v-text-field
          label="name"
          v-model="memberName"
          outlined
          dense>
      </v-text-field>
    </v-card-text>
    <v-card-actions>
      <v-spacer/>
      <v-btn
          v-if="isDuplicated"
          text
          color="orange accent-3"
          @click="checkDuplicated"
      > 중복체크
      </v-btn>
      <v-btn
          text
          color="teal accent-4"
          @click="join"
      > 회원가입
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import dialog from "@/utils/dialog";
import api from "@/utils/api";

export default {
  name: "Join",
  data() {
    return {
      id: '',
      password: '',
      memberName: '',
      isDuplicated: true
    }
  },
  methods: {
    promiseRejectHandler(err) {
      dialog.makeDialog({text: "시스템 에러가 발생했습니다."})
      console.log(err)
    },
    checkDuplicated() {
      if (this.id === '') {
        dialog.makeDialog({text: '먼저 아이디를 입력해주세요'})
        return
      }
      api({
        url: "/api/members/login",
        method: "post",
      })
          .then(this.duplicatedResponseHandler)
          .catch(this.promiseRejectHandler)
    },
    duplicatedResponseHandler(res) {
      return new Promise((resolve, reject) => {
        try {
          if (res.data.success) {
            if (res.data.contents) {
              dialog.makeDialog({text: `중복된 아이디입니다.`})
            } else {
              dialog.makeDialog({text: `회원가입이 가능한 아이디입니다.`})
              this.isDuplicated = false
            }
          } else {
            dialog.makeDialog({text: `${res.data.result.message}`})
          }
          resolve()
        } catch (error) {
          reject(error)
        }
      })
    },
    join() {
      if (this.id === '' || this.password === '' || this.memberName === '') {
        dialog.makeDialog({text: '필수정보를 모두 입력해주세요'})
        return
      }
      this.joinRequest(this.id, this.password, memberName)
    },
    joinRequest() {

    }
  }
}
</script>

<style scoped>

</style>