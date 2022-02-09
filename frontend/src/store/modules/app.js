// Pathify
import { make } from 'vuex-pathify'

const state = {
  items: [
    {
      title: "홈",
      to: '/',
      icon: ''
    },
    {
      title: '주문하기(상품목록)',
      to : '/product',
      icon: ''
    },
    {
      title: '주문목록',
      to : '/order-list',
      icon: ''
    },
  ],
  auth: false
}

const mutations = make.mutations(state)

const actions = {
  ...make.actions(state),
  init: async ({ dispatch }) => {
    //
    console.log(dispatch)
  },
}

const getters = {

}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
