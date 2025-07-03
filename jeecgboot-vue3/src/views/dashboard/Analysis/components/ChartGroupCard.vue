<template>
  <div class="md:flex">
    <template v-for="(item, index) in dataList" :key="item.title">
      <ChartCard
        :loading="loading"
        :title="item.title"
        :total="getTotal(item.total, index)"
        class="md:w-1/4 w-full !md:mt-0 !mt-4"
        :class="[index + 1 < 4 && '!md:mr-4']"
      >
        <template #action>
          <a-tooltip :title="item.desc">
            <Icon :icon="item.icon" :size="30" />
          </a-tooltip>
        </template>
        <div v-if="type === 'chart'">
          <Trend term="周同比" :percentage="item.weekPercent ? Math.abs(item.weekPercent) : 0" v-if="index === 0 || index === 1" :type="!item.weekPercent || item.weekPercent > 0"/>
          <Trend style="margin-left: 10px" term="日同比" :percentage="item.dayPercent ? Math.abs(item.dayPercent) : 0" v-if="index === 0 || index === 1" :type="!item.dayPercent || item.dayPercent>0" />

<!--          <SingleLine v-if="index === 1" :option="option" :chartData="chartData" :seriesColor="seriesColor" height="50px"></SingleLine>-->

          <Bar v-if="index === 2" :option="option" :chartData="chartData" :seriesColor="seriesColor" height="50px"></Bar>

<!--          <Progress v-if="index === 3" :percent="78" :show-info="false"></Progress>-->
        </div>
        <div v-else>
          <SingleLine :seriesColor="seriesColor" v-if="index === 0" :option="option" :chartData="chartData" height="50px"></SingleLine>

          <SingleLine :seriesColor="seriesColor" v-if="index === 1" :option="option" :chartData="chartData" height="50px"></SingleLine>

          <Bar :seriesColor="seriesColor" v-if="index === 2" :option="option" :chartData="chartData" height="50px"></Bar>

          <Progress v-if="index === 3" :percent="78" :show-info="false"></Progress>
        </div>
        <template #footer v-if="type === 'chart'">
          <span>
            {{ item.footer }}
            <span>{{ item.value }}</span>
          </span>
        </template>
        <template #footer v-else>
          <span
            >{{ item.footer }}<span>{{ item.value }}</span></span
          >
        </template>
      </ChartCard>
    </template>
  </div>
</template>
<script lang="ts" setup>
  import { ref, computed } from 'vue';
  import { Icon } from '/@/components/Icon';
  import { Progress } from 'ant-design-vue';
  import ChartCard from '/@/components/chart/ChartCard.vue';
  import Trend from '/@/components/chart/Trend.vue';
  import Bar from '/@/components/chart/Bar.vue';
  import SingleLine from '/@/components/chart/SingleLine.vue';
  import { chartCardList, bdcCardList } from '../data';
  import { useRootSetting } from '/@/hooks/setting/useRootSetting';

  const { getThemeColor } = useRootSetting();
  const props = defineProps({
    loading: {
      type: Boolean,
    },
    type: {
      type: String,
      default: 'chart',
    },
  });
  const option = ref({ xAxis: { show: false, boundaryGap: false }, yAxis: { show: false, boundaryGap: false, max: 220 } });

  const chartData = ref([
    {
      name: '1月',
      value: 50,
    },
    {
      name: '2月',
      value: 100,
    },
    {
      name: '3月',
      value: 150,
    },
    {
      name: '4月',
      value: 40,
    },
    {
      name: '5月',
      value: 110,
    },
    {
      name: '6月',
      value: 120,
    },
  ]);
  const seriesColor = computed(() => {
    return getThemeColor.value;
  });
  const dataList = computed(() => (props.type === 'dbc' ? bdcCardList : chartCardList));

  function getTotal(total, index) {
    return index === 0 || index === 2 || index === 3 ? `￥${total}` :  total;
  }
</script>
