<template>
<div>
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
</div>
</template>
<script>
import { sync } from 'vuex-pathify'
import CustomDialog from "@/components/CustomDialog";
export default {
name: "DefaultView",
  components: {CustomDialog},
  computed: {
    ...sync('dialog',[
      'dialogManager'
    ])
  },
}
</script>
<style>

</style>