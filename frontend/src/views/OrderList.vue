<template>
  <v-card class="mx-auto mt-15" max-width="1300px">
    <v-card-title>
      <div class="text-h5 font-weight-black mb-3">
        주문 목록
      </div>
    </v-card-title>
    <v-card-text>
      <v-data-table
          style="border-style: groove; border-width: 1px;"
          :height="tableHeight-150"
          fixed-header
          :items="orderListTableBody"
          :headers="orderListTableHeader"
          dense
      >
        <template
            v-slot:item="{ item, index }"
        >
          <tr
              :data-category-id="orderListTableHeader.id"
              :data-id="item.name">
            <td>{{ item.name }}</td>
            <td>{{ item.price }}</td>
            <td>{{ item.count }}</td>
            <td>{{ item.orderDate }}</td>
            <td>{{ item.totalPrice }}</td>
          </tr>
        </template>

      </v-data-table>
    </v-card-text>
  </v-card>
</template>

<script>
import api from "@/utils/api";
import dialog from "@/utils/dialog";

export default {
  name: "OrderList",
  data() {
    return {
      tableHeight: '0',
      orderListTableHeader: [
        {
          text: '상품명',
          value: 'name',
        },
        {
          text: '가격',
          value: 'price',
        },
        {
          text: '수량',
          value: 'count',
        },
        {
          text: '주문일',
          value: 'orderDate',
        },
        {
          text: '총 가격',
          value: 'totalPrice',
        },
      ],
      orderListTableBody: [
      ],
    }
  },
  mounted() {
    this.tableHeight = String(window.innerHeight - 200)
    window.addEventListener('resize', this.handleResize);
    this.getOrderList()
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
  },
  methods: {
    handleResize() {
      console.log('test')
      this.tableHeight = String(window.innerHeight - 200)
    },
    getOrderList() {
      api({
        url: '/api/order',
        method: "get"
      })
          .then(res => {
            if (res.data.success) {
              console.log(res.data.result.contents)
              this.orderListTableBody = res.data.result.contents
            } else {
              dialog.makeDialog({text: "주문내역을 받아오지 못했습니다."})
              console.log(res.data.result.message)
            }
          })
          .catch(err => {
            dialog.makeDialog({text: "상품목록을 받아오지 못했습니다."})
          })
    }
  },
}
</script>

<style>

</style>