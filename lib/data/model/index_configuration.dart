class IndexConfiguration {
  // 存储所有可用的索引类别
  final List<IndexCategory> availableCategories;

  // 存储用户选择的索引值
  final Map<String, String> selectedValues;

  IndexConfiguration({
    required List<IndexCategory> availableCategories,
    Map<String, String>? selectedValues,
  })  : availableCategories = availableCategories,
        selectedValues = _ensureSelections(availableCategories, selectedValues);

  // 保证每个类别都有选中项，默认选第一个
  static Map<String, String> _ensureSelections(
      List<IndexCategory> categories, Map<String, String>? selected) {
    final result = <String, String>{};
    for (var cat in categories) {
      result[cat.paramKey] = selected?[cat.paramKey] ?? cat.options.first.paramValue;
    }
    return result;
  }

  // 添加索引类别
  IndexConfiguration addCategory(IndexCategory category) {
    final newCategories = [...availableCategories, category];
    final newSelected = {...selectedValues, category.paramKey: category.options.first.paramValue};
    return IndexConfiguration(
      availableCategories: newCategories,
      selectedValues: newSelected,
    );
  }

  // 移除索引类别
  IndexConfiguration removeCategory(String paramKey) {
    final newCategories = availableCategories
        .where((category) => category.paramKey != paramKey)
        .toList();
    final newSelected = Map<String, String>.from(selectedValues)..remove(paramKey);
    return IndexConfiguration(
      availableCategories: newCategories,
      selectedValues: newSelected,
    );
  }

  // 选择索引值
  IndexConfiguration selectValue(String paramKey, String paramValue) {
    final newSelected = {...selectedValues, paramKey: paramValue};
    return IndexConfiguration(
      availableCategories: availableCategories,
      selectedValues: newSelected,
    );
  }

  // 取消选择索引值时重置为第一个可选项
  IndexConfiguration unselectValue(String paramKey) {
    final category = availableCategories.firstWhere((cat) => cat.paramKey == paramKey);
    final newSelected = {...selectedValues, paramKey: category.options.first.paramValue};
    return IndexConfiguration(
      availableCategories: availableCategories,
      selectedValues: newSelected,
    );
  }

  // 获取所有选择的索引值
  Map<String, String> getSelectedValues() {
    return Map<String, String>.from(selectedValues);
  }

  // 获取特定类别的选项列表
  List<IndexOption> getOptionsForCategory(String paramKey) {
    final category = availableCategories.firstWhere(
        (cat) => cat.paramKey == paramKey,
        orElse: () => throw Exception('Category not found'));
    return category.options;
  }

  // 获取特定类别的当前选择值
  String? getSelectedValueForCategory(String paramKey) {
    return selectedValues[paramKey];
  }

  // 清空所有选择时重置为每个类别的第一个可选项
  IndexConfiguration clearAllSelections() {
    final newSelected = {
      for (var cat in availableCategories) cat.paramKey: cat.options.first.paramValue
    };
    return IndexConfiguration(
      availableCategories: availableCategories,
      selectedValues: newSelected,
    );
  }

  // 复制对象
  IndexConfiguration copyWith({
    List<IndexCategory>? availableCategories,
    Map<String, String>? selectedValues,
  }) {
    final categories = availableCategories ?? this.availableCategories;
    final selected = selectedValues ?? this.selectedValues;
    return IndexConfiguration(
      availableCategories: categories,
      selectedValues: selected,
    );
  }
}

class IndexCategory {
  final String paramKey; // 接口参数字段
  final String displayName; // 显示名称
  final List<IndexOption> options; // 可选项列表

  IndexCategory({
    required this.paramKey,
    required this.displayName,
    required this.options,
  });

  // 从Map创建（可选）
  factory IndexCategory.fromMap(Map<String, dynamic> map) {
    return IndexCategory(
      paramKey: map['paramKey'],
      displayName: map['displayName'],
      options: (map['options'] as List)
          .map((option) => IndexOption.fromMap(option))
          .toList(),
    );
  }

  // 转换为Map（可选）
  Map<String, dynamic> toMap() {
    return {
      'paramKey': paramKey,
      'displayName': displayName,
      'options': options.map((option) => option.toMap()).toList(),
    };
  }
}

class IndexOption {
  final String displayText; // 显示文本
  final String paramValue; // 接口参数值

  IndexOption({
    required this.displayText,
    required this.paramValue,
  });

  // 从Map创建（可选）
  factory IndexOption.fromMap(Map<String, dynamic> map) {
    return IndexOption(
      displayText: map['displayText'],
      paramValue: map['paramValue'],
    );
  }

  // 转换为Map（可选）
  Map<String, dynamic> toMap() {
    return {
      'displayText': displayText,
      'paramValue': paramValue,
    };
  }

  @override
  String toString() {
    return displayText;
  }
}
