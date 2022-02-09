<template>
  <div>
    <core-header />
    <core-view />
  </div>
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