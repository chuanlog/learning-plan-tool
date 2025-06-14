-- =========================================
-- 1) 插入所有课程（已剔除“英”版物理课，去重重复项，补齐遗漏课程）
-- =========================================
INSERT INTO course (course_name, credits, total_hours, course_type, user_id)
VALUES
    -- 公共基础课 · 数学基础 (type=0)
    ('微积分A(1)', 5, 80, 0, 1),
    ('微积分A(2)', 5, 80, 0, 1),
    ('线性代数', 4, 64, 0, 1),
    ('高等线性代数选讲', 2, 32, 0, 1),
    ('概率论与随机过程', 3, 48, 0, 1),
    ('概率论与数理统计', 3, 48, 0, 1),
    ('复变函数引论', 2, 32, 0, 1),
    ('数值分析', 3, 48, 0, 1),
    ('数学实验', 4, 64, 0, 1),
    ('离散数学(1)', 3, 48, 0, 1),
    ('离散数学(2)', 3, 48, 0, 1),

    -- 公共基础课 · 自然科学基础 (type=0)
    ('大学物理B(1)', 4, 64, 0, 1),
    ('大学物理(1)', 4, 64, 0, 1),
    ('大学物理B(2)', 4, 64, 0, 1),
    ('大学物理(2)', 4, 64, 0, 1),

    -- 专业必修课 · 学科基础 (type=1)
    ('信息科学技术概论', 1, 16, 1, 1),

    -- 专业必修课 · 主干课程 (type=1)
    ('程序设计基础', 3, 48, 1, 1),
    ('面向对象程序设计基础', 2, 32, 1, 1),
    ('数字逻辑电路', 3, 48, 1, 1),
    ('数字逻辑设计', 3, 48, 1, 1),
    ('数字逻辑实验', 1, 16, 1, 1),
    ('数据结构', 4, 64, 1, 1),
    ('计算机系统概论', 3, 48, 1, 1),
    ('信号处理原理', 3, 48, 1, 1),
    ('信号与系统', 4, 64, 1, 1),
    ('计算机网络原理', 3, 48, 1, 1),
    ('计算机组成原理', 4, 64, 1, 1),
    ('软件工程', 3, 48, 1, 1),
    ('操作系统', 3, 48, 1, 1),
    ('计算机系统结构', 3, 48, 1, 1),
    ('形式语言与自动机', 2, 32, 1, 1),
    ('编译原理', 2, 32, 1, 1),
    ('人工智能导论', 2, 32, 1, 1),
    ('网络空间安全导论', 3, 48, 1, 1),
    -- 补齐遗漏课程
    ('算法设计与分析', 3, 48, 1, 1),
    ('C 语言程序设计', 3, 48, 1, 1),

    -- 专业选修课 (type=2)
    ('微计算机技术', 3, 48, 2, 1),
    ('数字系统设计自动化', 2, 32, 2, 1),
    ('VLSI设计导论', 2, 32, 2, 1),
    ('通信原理概论', 3, 48, 2, 1),
    ('计算机网络安全技术', 2, 32, 2, 1),
    ('存储技术基础', 2, 32, 2, 1),
    ('高性能计算前沿技术', 1, 16, 2, 1),
    ('网络安全工程与实践', 2, 32, 2, 1),
    ('计算机网络管理', 2, 32, 2, 1),
    ('无线移动网络技术', 2, 32, 2, 1),
    ('互联网工程设计', 2, 32, 2, 1),
    ('网络编程技术', 2, 32, 2, 1),
    ('现代密码学', 2, 32, 2, 1),
    ('初等数论', 2, 32, 2, 1),
    ('高性能计算导论', 2, 32, 2, 1),
    ('数据库系统概论', 2, 32, 2, 1),
    ('软件开发方法', 2, 32, 2, 1),
    ('计算机软件前沿技术', 1, 16, 2, 1),
    ('数据挖掘', 2, 32, 2, 1),
    ('量子计算研讨课', 3, 48, 2, 1),
    ('计算理论导引', 2, 32, 2, 1),
    ('模式识别', 2, 32, 2, 1),
    ('数字图像处理', 2, 32, 2, 1),
    ('多媒体技术基础及应用', 2, 32, 2, 1),
    ('计算机图形学基础', 2, 32, 2, 1),
    ('系统仿真与虚拟现实', 2, 32, 2, 1),
    ('现代控制技术', 2, 32, 2, 1),
    ('信息检索', 2, 32, 2, 1),
    ('机器学习概论', 2, 32, 2, 1),
    ('人机交互理论与技术', 2, 32, 2, 1),
    ('人工神经网络', 2, 32, 2, 1),
    ('媒体计算', 2, 32, 2, 1),
    ('搜索引擎技术基础', 2, 32, 2, 1),
    ('系统分析与控制', 3, 48, 2, 1),
    ('嵌入式系统', 2, 32, 2, 1),
    ('人工智能技术与实践', 2, 32, 2, 1),
    ('虚拟现实技术', 2, 32, 2, 1),
    ('计算机网络专题训练', 2, 32, 2, 1),
    ('操作系统专题训练', 2, 32, 2, 1),
    ('编译原理专题训练', 2, 32, 2, 1),
    ('数据库专题训练', 2, 32, 2, 1),
    ('以服务为中心的软件开发设计与实现', 2, 32, 2, 1),
    ('认知机器人', 1, 16, 2, 1),
    ('物理实验A(1)', 2, 32, 2, 1),
    ('物理实验B(1)', 1, 16, 2, 1),
    ('物理实验A(2)', 2, 32, 2, 1),
    ('物理实验B(2)', 1, 16, 2, 1),
    ('电子学基础', 2, 32, 2, 1),
    ('电子学基础实验', 1, 16, 2, 1),

    -- 专业实践环节 (type=4)
    ('程序设计训练', 2, 32, 4, 1),
    ('专业实践', 2, 32, 4, 1),
    ('综合论文训练', 15, 240, 4, 1),

    -- 体育课 (type=6)
    ('大学体育—篮球', 1, 16, 6, 1),
    ('大学体育—足球', 1, 16, 6, 1),
    ('大学体育—定向越野', 1, 16, 6, 1),
    ('大学体育—羽毛球', 1, 16, 6, 1),

    -- 思想政治理论课 (type=3)
    ('思想道德与法治', 3, 48, 3, 1),
    ('形势与政策（1）-秋—组1', 1, 16, 3, 1),
    ('形势与政策（1）-秋—组2', 1, 16, 3, 1),
    ('形势与政策（2）-春', 1, 16, 3, 1),
    ('形势与政策（2）-秋', 1, 16, 3, 1),
    ('中国近现代史纲要', 3, 48, 3, 1),
    ('马克思主义基本原理', 3, 48, 3, 1),
    ('毛泽东思想和中国特色社会主义理论体系概论', 2, 32, 3, 1),
    ('习近平新时代中国特色社会主义思想概论', 2, 32, 3, 1),

    -- 思政实践（暑期选修） (type=3)
    ('思政实践（暑期）', 2, 32, 3, 1),

    -- 限选“四史”课程 (type=3)
    ('社会主义发展史（“四史”）', 1, 16, 3, 1),
    ('中国共产党历史（“四史”）', 1, 16, 3, 1),
    ('中华人民共和国史（“四史”）', 1, 16, 3, 1),
    ('改革开放史（“四史”）', 1, 16, 3, 1),

    -- 思政限选及文化类通识课 (type=3)
    ('生态文明十五讲', 2, 32, 3, 1),
    ('当代科学中的哲学问题', 2, 32, 3, 1),
    ('环境保护与可持续发展', 1, 16, 3, 1),
    ('新闻中的文化', 1, 16, 3, 1),
    ('悦读马克思', 2, 32, 3, 1),
    ('当代法国思想与文化研究', 2, 32, 3, 1),
    ('孔子和鲁迅', 2, 32, 3, 1),
    ('媒介史与媒介哲学', 2, 32, 3, 1),
    ('教育哲学', 2, 32, 3, 1),
    ('中国历史地理', 2, 32, 3, 1),
    ('西方近代哲学', 3, 48, 3, 1),
    ('气候变化与全球发展', 3, 48, 3, 1),
    ('腐败的政治经济学', 2, 32, 3, 1),
    ('中美贸易争端和全球化重构', 2, 32, 3, 1),
    ('西方政治制度', 2, 32, 3, 1),
    ('社会学的想象力：结构、权力与转型', 3, 48, 3, 1),

    -- 国防与政治课程 (type=3)
    ('当代国防系列讲座', 1, 16, 3, 1),
    ('高技术战争', 1, 16, 3, 1),
    ('中国国情与发展', 3, 48, 3, 1),
    ('中国政府与政治', 2, 32, 3, 1),
    ('国际关系分析', 4, 64, 3, 1),
    ('中国宏观经济分析', 2, 32, 3, 1),
    ('现代化与全球化思想研究', 2, 32, 3, 1),

    -- 外语 (type=3)
    ('英语综合训练（C1）', 4, 64, 3, 1),
    ('英语综合训练（C2）', 4, 64, 3, 1),
    ('第二外语（选修）', 4, 64, 3, 1),

    -- 写作与沟通 (type=3)
    ('写作与沟通', 2, 32, 3, 1),

    -- 通识选修课 (type=3)
    ('艺术欣赏', 2, 32, 3, 1),
    ('文化与传播', 2, 32, 3, 1),
    ('科技前沿导读', 2, 32, 3, 1),
    ('社会调查方法', 2, 32, 3, 1),

    -- 军事课程 (type=5)
    ('军事理论', 2, 32, 5, 1),
    ('军事技能', 2, 32, 5, 1)
;
-- =========================================
-- 2) 插入所有先修关系
-- =========================================
INSERT INTO course_prerequisite (course_id, prerequisite_id)
-- 微积分A(2) ← 微积分A(1)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '微积分A(1)'
WHERE c.course_name = '微积分A(2)';

-- 离散数学(2) ← 离散数学(1)
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '离散数学(1)'
WHERE c.course_name = '离散数学(2)';

-- 数值分析 ← 概率论与随机过程
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '概率论与随机过程'
WHERE c.course_name = '数值分析';

-- 数学实验 ← 离散数学(1)
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '离散数学(1)'
WHERE c.course_name = '数学实验';

-- 大学物理B(2) ← 大学物理B(1)
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '大学物理B(1)'
WHERE c.course_name = '大学物理B(2)';

-- 面向对象程序设计基础 ← 程序设计基础
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '程序设计基础'
WHERE c.course_name = '面向对象程序设计基础';

-- 数字逻辑设计 ← 数字逻辑电路
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数字逻辑电路'
WHERE c.course_name = '数字逻辑设计';

-- 数字逻辑实验 ← 数字逻辑电路
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数字逻辑电路'
WHERE c.course_name = '数字逻辑实验';

-- 数据结构 ← 程序设计基础
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '程序设计基础'
WHERE c.course_name = '数据结构';

-- 计算机系统概论 ← 数据结构
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数据结构'
WHERE c.course_name = '计算机系统概论';

-- 信号与系统 ← 信号处理原理
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '信号处理原理'
WHERE c.course_name = '信号与系统';

-- 计算机网络原理 ← 计算机系统概论
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '计算机系统概论'
WHERE c.course_name = '计算机网络原理';

-- 计算机组成原理 ← 数字逻辑电路
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数字逻辑电路'
WHERE c.course_name = '计算机组成原理';

-- 操作系统 ← 数据结构
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数据结构'
WHERE c.course_name = '操作系统';

-- 软件工程 ← 数据结构
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数据结构'
WHERE c.course_name = '软件工程';

-- 编译原理 ← 形式语言与自动机
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '形式语言与自动机'
WHERE c.course_name = '编译原理';

-- 人工智能导论 ← 数据结构
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数据结构'
WHERE c.course_name = '人工智能导论';

-- 网络空间安全导论 ← 计算机网络原理
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '计算机网络原理'
WHERE c.course_name = '网络空间安全导论';

-- 微计算机技术 ← 数字逻辑电路
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数字逻辑电路'
WHERE c.course_name = '微计算机技术';

-- VLSI设计导论 ← 数字系统设计自动化
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数字系统设计自动化'
WHERE c.course_name = 'VLSI设计导论';

-- 通信原理概论 ← 信号与系统
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '信号与系统'
WHERE c.course_name = '通信原理概论';

-- 计算机网络安全技术 ← 计算机网络原理
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '计算机网络原理'
WHERE c.course_name = '计算机网络安全技术';

-- 存储技术基础 ← 计算机组成原理
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '计算机组成原理'
WHERE c.course_name = '存储技术基础';

-- 高性能计算前沿技术 ← 算法设计与分析
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '算法设计与分析'
WHERE c.course_name = '高性能计算前沿技术';

-- 数据库系统概论 ← 数据结构
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数据结构'
WHERE c.course_name = '数据库系统概论';

-- 数据挖掘 ← 数据库系统概论
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数据库系统概论'
WHERE c.course_name = '数据挖掘';

-- 初等数论 ← 离散数学(1)
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '离散数学(1)'
WHERE c.course_name = '初等数论';

-- 量子计算研讨课 ← 计算理论导引
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '计算理论导引'
WHERE c.course_name = '量子计算研讨课';

-- 计算理论导引 ← 离散数学(1)
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '离散数学(1)'
WHERE c.course_name = '计算理论导引';

-- 模式识别 ← 人工智能导论
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '人工智能导论'
WHERE c.course_name = '模式识别';

-- 数字图像处理 ← 多媒体技术基础及应用
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '多媒体技术基础及应用'
WHERE c.course_name = '数字图像处理';

-- 系统仿真与虚拟现实 ← 嵌入式系统
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '嵌入式系统'
WHERE c.course_name = '系统仿真与虚拟现实';

-- 嵌入式系统 ← C 语言程序设计
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = 'C 语言程序设计'
WHERE c.course_name = '嵌入式系统';

-- 编译原理专题训练 ← 编译原理
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '编译原理'
WHERE c.course_name = '编译原理专题训练';

-- 数据库专题训练 ← 数据库系统概论
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '数据库系统概论'
WHERE c.course_name = '数据库专题训练';

-- 以服务为中心的软件开发设计与实现 ← 软件开发方法
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '软件开发方法'
WHERE c.course_name = '以服务为中心的软件开发设计与实现';

-- 认知机器人 ← 人工智能导论
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '人工智能导论'
WHERE c.course_name = '认知机器人';

-- 物理实验B(1) ← 物理实验A(1)
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '物理实验A(1)'
WHERE c.course_name = '物理实验B(1)';

-- 物理实验B(2) ← 物理实验A(2)
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '物理实验A(2)'
WHERE c.course_name = '物理实验B(2)';

-- 电子学基础实验 ← 电子学基础
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '电子学基础'
WHERE c.course_name = '电子学基础实验';

-- 程序设计训练 ← 程序设计基础
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '程序设计基础'
WHERE c.course_name = '程序设计训练';

-- 综合论文训练 ← 程序设计训练
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '程序设计训练'
WHERE c.course_name = '综合论文训练';

-- 综合论文训练 ← 专业实践
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '专业实践'
WHERE c.course_name = '综合论文训练';

-- 1) 专业选修 A1 方向补充：网络编程技术、互联网工程设计 ← 计算机网络原理
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '计算机网络原理'
WHERE c.course_name = '网络编程技术';

INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '计算机网络原理'
WHERE c.course_name = '互联网工程设计';

-- 2) 专业选修 A3 方向补充：虚拟现实技术 ← 系统仿真与虚拟现实
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '系统仿真与虚拟现实'
WHERE c.course_name = '虚拟现实技术';

-- 3) 专题训练补充：嵌入式系统专题训练 ← 嵌入式系统
INSERT INTO course_prerequisite (course_id, prerequisite_id)
SELECT c.id, p.id
FROM course c
         JOIN course p ON p.course_name = '嵌入式系统'
WHERE c.course_name = '嵌入式系统专题训练';
