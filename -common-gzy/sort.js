/**
 * Created by Administrator on 2019/3/19.
 */
var arr = [3,44,38,5,47,15,36,26,27,2,46,4,19,50,48];

/**
 * 计数排序--时间空间复杂度O(n+k)
 */
function countingsort(arr, maxValue){
    var bucket = new Array(maxValue + 1);
    var sortIndex = 0;
    var arrlenth = arr.length;
    var bucketlen = maxValue + 1;
    for(var i = 0; i < arrlenth; i++){
        if(!bucket[arr[i]]){
            bucket[arr[i]] = 0;
        }
        bucket[arr[i]]++;
    }
    for(var j = 0; j < bucketlen; j++){
        while(bucket[j] > 0){
            arr[sortIndex++] = j;
            bucket[j]--;
        }
    }
    return arr;
}


function swap(arr, i, j){
    arr[i] = arr[i]^arr[j];
    arr[j] = arr[i]^arr[j];
    arr[i] = arr[i]^arr[j];
    /*var tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;*/
}

/**
 * 堆排序--时间复杂度O(logn)、空间复杂度O(1)
 * 堆排序利用堆数据结构，类似于二叉树，每一个子节点小于父节点。那么root节点就是最大值
 * 堆排序分三步：
 * 1》所有元素构建大顶堆(R1,R2...Rn)
 * 2》堆顶元素和堆尾元素交换，得到(R1,R2...Rn-1)和Rn
 * 3》递归重新构建大堆顶
 */
var len;//多个函数共享可变数据长度

function heapsort(arr){
    buildMaxHeap(arr);
    for(var i = arr.length - 1; i > 0; i--){
        swap(arr, 0, i);
        len--;
        heapify(arr, 0);
    }
    return arr;
}

function buildMaxHeap(arr){//构建大堆顶
    len = arr.length;
    for(var i = Math.floor(len / 2); i >= 0; i--){
        heapify(arr, i);
    }
}

function heapify(arr, i){//堆调整
    var left = 2 * i + 1;
    var right = 2 * i +2;
    var largest = i;
    if(left < len && arr[left] > arr[largest]){
        largest = left;
    }
    if(right < len && arr[right] > arr[largest]){
        largest = right;
    }
    if(largest != i){
        swap(arr, i, largest);
        heapify(arr, largest);
    }
}

/**
 * 快速排序--空间复杂度O(logn)、时间复杂度O(logn)-O(n^2)
 */
function quicksort(arr, left, right){
    var len = arr.length;
    var partitionIndex;
    left = typeof(left) != 'number' ? 0 : left;
    right = typeof(right) != 'number' ? len - 1 : right;
    if(left < right){
        partitionIndex = partition(arr, left, right);
        quicksort(arr, left, partitionIndex - 1);
        quicksort(arr, partitionIndex + 1, right);
    }
    return arr;
}

function partition(arr, left, right){
    var pivot = left, index = pivot + 1;
    for(var i = index; i <= right; i++){
        if(arr[i] < arr[pivot]){
            swap(arr, i, index);
            index++;
        }
    }
    swap(arr, pivot, index - 1);
    return index - 1;
}

/**
 * 归并排序--时间复杂度O(logn)、空间复杂度O(n)
 */
function mergesort(arr){
    var len = arr.length;
    if(len < 2){
        return arr;
    }
    var middle = Math.floor(len/2);
    var left = arr.slice(0, middle);
    var right = arr.slice(middle);
    return merge(mergesort(left), mergesort(right));//递归子数组排序
}

function merge(left, right){
    var result = [];
    while(left.length > 0 && right.length > 0){
        if(left[0] < right [0]){
            result.push(left.shift())
        }else{
            result.push(right.shift())
        }
    }
    while(left.length)
        result.push(left.shift());
    while(right.length)
        result.push(right.shift());
    return result;
}