<template>
  <v-card>
    <v-card-title class="text-h5" v-if="question">
      confirm
    </v-card-title>
    <v-card-title class="text-h5" v-if="!question">
      alert
    </v-card-title>
    <v-card-text v-text="text"></v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        v-if="!question"
        color="green darken-1"
        text
        @click="$emit('submit')"
      >
        ok
      </v-btn>
      <v-btn
        v-if="question"
        color="green darken-1"
        text
        @click="$emit('submit')"
      >
        yes
      </v-btn>
      <v-btn
        v-if="question"
        color="green darken-1"
        text
        @click="$emit('hide')"
      >
        no
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'CustomDialog',
  props: ["text", "question"],
}
</script>
<style>

</style>