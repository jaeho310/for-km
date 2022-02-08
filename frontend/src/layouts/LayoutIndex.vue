<template>
  <v-app>
    <core-header />
    <core-view />
  </v-app>
</template>
<script>

export default {
  name: "LayoutIndex",
  components: {
    CoreHeader: () => import(
      '@/layouts/Header'
    ),
    CoreView: () => import(
      '@/layouts/View'
    )
  },
}
</script>
<style lang="">

</style>