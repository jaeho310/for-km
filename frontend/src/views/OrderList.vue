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
        {
          'name': 'a',
          'price': 100,
          'count': 1,
          'orderDate': '2012',
          'totalPrice': 100
        },
      ]
    }
  },
  mounted() {
    this.tableHeight = String(window.innerHeight - 200)
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
  },
  methods: {
    handleResize() {
      console.log('test')
      this.tableHeight = String(window.innerHeight - 200)
    }
  }

}
</script>

<style>

</style>