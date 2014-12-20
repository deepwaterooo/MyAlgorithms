#include <stdio.h>
//#include <stdlib.h> // rand sranddev
#include <cstdlib>

#define DEBUG 1
#define SORT_NUM 10
 
void print_array(int *list, int len);
void merge_array(int *list1, int list1_size, int *list2, int list2_size);
  
void merge_sort(int *list, int list_size) {
    if (list_size > 1) {	
	// ������ƽ���ֳ���������
	int *list1 = list;
	int list1_size = list_size / 2;
	int *list2 = list + list_size / 2;
	int list2_size = list_size - list1_size;
	// �ֱ�鲢����
	merge_sort(list1, list1_size);
	merge_sort(list2, list2_size);
 
	// �鲢
	merge_array(list1, list1_size, list2, list2_size);
    }
}
 
void merge_array(int *list1, int list1_size, int *list2, int list2_size) {
    int i, j, k;
    i = j = k = 0;
 
    // ������ʱ�������ڴ洢�鲢���
    int list[list1_size + list2_size];
 
    // note: ֻҪ��һ�����鵽����β����Ҫ����
    // Ҳ����˵ֻ��������û�е���β����ʱ���ִ�����ѭ��
    while (i < list1_size && j < list2_size) {	
	// �ѽ�С���Ǹ����ݷŵ��������� ͬʱ�ƶ�ָ��
	list[k++] = list1[i] < list2[j] ? list1[i++] : list2[j++];
    }
 
    // ��� list1 ����Ԫ�أ���ʣ�µ�����ֱ�ӷŵ��������
    while (i < list1_size) {	
	list[k++] = list1[i++];
    }
 
    // ��� list2 ����Ԫ�أ���ʣ�µ�����ֱ�ӷŵ��������
    while (j < list2_size) {	
	list[k++] = list2[j++];
    }
 
    // �ѽ������ copy �� list1 ��
    for (int ii = 0; ii < (list1_size + list2_size); ++ii) {	
	list1[ii] = list[ii];
    }
 
}
 
void print_array(int *list, int len) {
    int i;
    for (i = 0; i < len; ++i) {	
	// printf("%3d", *(list+i));
	printf("%3d", list[i]);
	if (i < len - 1)
	    printf(" ");
    }
    printf("\n");
}
 
int main(void) {
    int len = SORT_NUM;
    int list[len];
    for (int i = 0; i < len; ++i) {	
	//sranddev();
	//srand();
	list[i] = rand() % (SORT_NUM * SORT_NUM);
    }
 
    print_array(list, len);
    merge_sort(list, len);
    print_array(list, len);
 
    return 0;
}
