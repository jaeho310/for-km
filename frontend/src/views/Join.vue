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
          type="password"
          label="password"
          v-model="password"
          outlined
          dense>
      </v-text-field>
      <v-text-field
          label="email"
          v-model="email"
          @click="isDuplicated=true"
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
      email: '',
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
        dialog.makeDialog({text: '아이디를 입력해주세요'})
        return
      }
      if (this.email === '') {
        dialog.makeDialog({text: '이메일을 입력해주세요'})
        return
      }
      api({
        url: `/api/members/exist?memberId=${this.id}&email=${this.email}`,
        method: "get",
      })
          .then(this.duplicatedResponseHandler)
          .catch(this.promiseRejectHandler)
    },
    toLogin() {
      this.$router.push("/login")
    },
    duplicatedResponseHandler(res) {
      return new Promise((resolve, reject) => {
        try {
          if (res.data.success) {
            if (res.data.result.contents) {
              dialog.makeDialog({text: `중복된 아이디입니다.`})
            } else {
              dialog.makeDialog({text: `회원가입이 가능한 아이디와 이메일입니다.`})
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
      if (this.id === '' || this.password === '' || this.memberName === '' || this.email === '') {
        dialog.makeDialog({text: '필수정보를 모두 입력해주세요'})
        return
      }
      if (this.isDuplicated) {
        dialog.makeDialog({text: 'id 중복체크를 해주세요'})
        return
      }
      this.joinRequest()
    },
    joinRequest() {
      api({
        url: "/api/members/join",
        method: "post",
        data: {
          'customMemberId': this.id,
          'name': this.memberName,
          'password': this.password,
          'email': this.email
        }
      })
          .then(res => {
            console.log(res)
            if (res.data.success) {
              dialog.makeDialog({text: `가입되었습니다.`, callback: this.toLogin})
            } else {
              console.log(res.data.result.message)
              dialog.makeDialog({text: `시스템에러로 회원가입에 실패했습니다.`})
            }
          })
          .catch(this.promiseRejectHandler)
    }
  }
}
</script>

<style scoped>

</style>