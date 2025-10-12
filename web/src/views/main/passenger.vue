<template>
  <p>
    <a-space>
      <a-button type="primary" @click="handleQuery()">刷新</a-button>
      <a-button type="primary" @click="onAdd">新增</a-button>
    </a-space>
  </p>
  <a-table :dataSource="passengers"
           :columns="columns"
           :pagination="pagination"
           @change="handleTableChange"
           :loading="loading">
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'operation'">
        <a-space>
          <a-popconfirm
              title="删除后不可恢复，确认删除?"
              @confirm="onDelete(record)"
              ok-text="确认" cancel-text="取消">
            <a style="color: red">删除</a>
          </a-popconfirm>
          <a @click="onEdit(record)">编辑</a>
        </a-space>
      </template>
      <template v-else-if="column.dataIndex === 'type'">
        <span v-for="item in PASSENGER_TYPE_ARRAY" :key="item.code">
          <span v-if="item.code === record.type">
            {{item.desc}}
          </span>
        </span>
      </template>
    </template>
  </a-table>
  <a-modal v-model:visible="visible" title="乘车人" @ok="handleOk"
           ok-text="确认" cancel-text="取消">
    <a-form :model="passenger" :label-col="{span: 4}" :wrapper-col="{ span: 20 }">
      <a-form-item label="姓名">
        <a-input v-model:value="passenger.name" />
      </a-form-item>
      <a-form-item label="身份证">
        <a-input v-model:value="passenger.idCard" />
      </a-form-item>
      <a-form-item label="旅客类型">
        <a-select v-model:value="passenger.type">
          <a-select-option v-for="item in PASSENGER_TYPE_ARRAY" :key="item.code" :value="item.code">
            {{item.desc}}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import { defineComponent, ref, onMounted } from 'vue';
import { notification } from 'ant-design-vue';
import api from '@/utils/request'; // ✅ 用带拦截器的实例

export default defineComponent({
  name: 'passenger-view',
  setup() {
    const PASSENGER_TYPE_ARRAY = window.PASSENGER_TYPE_ARRAY;
    const visible = ref(false);
    const passenger = ref({
      id: undefined,
      memberId: undefined,
      name: undefined,
      idCard: undefined,
      type: undefined,
      createTime: undefined,
      updateTime: undefined,
    });
    const passengers = ref([]);
    const pagination = ref({
      total: 0,
      current: 1,
      pageSize: 10,
    });
    const loading = ref(false);

    const columns = [
      { title: '姓名', dataIndex: 'name', key: 'name' },
      { title: '身份证', dataIndex: 'idCard', key: 'idCard' },
      { title: '旅客类型', dataIndex: 'type', key: 'type' },
      { title: '操作', dataIndex: 'operation' }
    ];

    const onAdd = () => {
      passenger.value = {};
      visible.value = true;
    };

    const onEdit = (record) => {
      passenger.value = window.Tool.copy(record);
      visible.value = true;
    };

    const onDelete = async (record) => {
      try {
        const { data } = await api.delete(`/passenger/delete/${record.id}`);
        if (data.success) {
          notification.success({ description: '删除成功！' });
          await handleQuery({ page: pagination.value.current, size: pagination.value.pageSize });
        } else {
          notification.error({ description: data.message });
        }
      } catch (e) {
        notification.error({ description: '删除失败' });
      }
    };

    const handleOk = async () => {
      try {
        const { data } = await api.post('/passenger/save', passenger.value);
        if (data.success) {
          notification.success({ description: '保存成功！' });
          visible.value = false;
          await handleQuery({ page: pagination.value.current, size: pagination.value.pageSize });
        } else {
          notification.error({ description: data.message });
        }
      } catch (e) {
        notification.error({ description: '保存失败' });
      }
    };

    const handleQuery = async (param) => {
      const p = param || { page: 1, size: pagination.value.pageSize };
      loading.value = true;
      try {
        const { data } = await api.get('/passenger/query-list', {
          params: { page: p.page, size: p.size }
        });
        if (data.success) {
          const content = data.content || { list: [], total: 0 };
          passengers.value = content.list || [];
          pagination.value.current = p.page;
          pagination.value.total = content.total ?? 0;
        } else {
          notification.error({ description: data.message });
        }
      } finally {
        loading.value = false;
      }
    };

    const handleTableChange = (pager) => {
      handleQuery({ page: pager.current, size: pager.pageSize });
    };

    onMounted(() => {
      // 调试：确认拦截器能读到 token
      console.log('[LS token]', localStorage.getItem('token'));
      handleQuery({ page: 1, size: pagination.value.pageSize });
    });

    return {
      PASSENGER_TYPE_ARRAY,
      passenger,
      visible,
      passengers,
      pagination,
      columns,
      handleTableChange,
      handleQuery,
      loading,
      onAdd,
      handleOk,
      onEdit,
      onDelete
    };
  },
});
</script>