<template>
<v-app>
  <router-view />
  <v-dialog
      max-width="400"
      v-model="dialogManager.showDialog"
      >
      <custom-dialog
        :text="dialogManager.dialogInfo.text"
        :question="dialogManager.dialogInfo.question"
        @hide="dialogManager.dialogInfo.hideDialog"
        @submit="dialogManager.dialogInfo.submitDialog"
      />
  </v-dialog>
</v-app>
</template>
<script>
import { sync } from 'vuex-pathify'
export default {
name: "DefaultView",
computed: {
    ...sync('dialog',[
      'dialogManager'
    ])
  },
}
</script>
<style>

</style>