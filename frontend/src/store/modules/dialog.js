// Pathify
import { make } from 'vuex-pathify'

const state = {
  dialogManager: {
    dialogInfo: {
      text: "Dialog 메시지를 입력하세요",
      question: false,
      hideDialog: null,
      submitDialog: null,
    },
    showDialog: false,
    dialogCallback: ()=>{alert("dialog 콜백을 확인해주세요")}
  }
}

const mutations = make.mutations(state)

const actions = {}

const getters = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
