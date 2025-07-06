// src/shims-js-cookie.d.ts
declare module 'js-cookie' {
    interface CookieAttributes {
        /**
         * 定义 cookie 的有效期
         * 可以设置为数字（天数）或 Date 对象
         */
        expires?: number | Date;
        /**
         * 定义 cookie 有效的路径
         * @default '/'
         */
        path?: string;
        /**
         * 定义 cookie 有效的域名
         */
        domain?: string;
        /**
         * 表示 cookie 只通过安全协议传输
         */
        secure?: boolean;
        /**
         * 控制 cookie 的跨站请求行为
         */
        sameSite?: 'strict' | 'Strict' | 'lax' | 'Lax' | 'none' | 'None';
    }

    /**
     * 设置 cookie
     * @param name cookie 名称
     * @param value cookie 值
     * @param options cookie 选项
     */
    function set(name: string, value: string, options?: CookieAttributes): void;

    /**
     * 设置 cookie（对象形式）
     * @param name cookie 名称
     * @param value cookie 值（任意对象，将被转换为 JSON）
     * @param options cookie 选项
     */
    function set(name: string, value: any, options?: CookieAttributes): void;

    /**
     * 获取指定名称的 cookie 值
     * @param name cookie 名称
     */
    function get(name: string): string | undefined;

    /**
     * 获取所有 cookie
     */
    function get(): { [key: string]: string };

    /**
     * 获取指定名称的 cookie 值（JSON 解析）
     * @param name cookie 名称
     */
    function getJSON<T = any>(name: string): T | undefined;

    /**
     * 移除指定 cookie
     * @param name cookie 名称
     * @param options cookie 选项（需与设置时一致）
     */
    function remove(name: string, options?: CookieAttributes): void;

    /**
     * 移除多个 cookie
     * @param names cookie 名称数组
     * @param options cookie 选项
     */
    function remove(...names: string[]): void;

    /**
     * 创建具有自定义属性的新 cookie 实例
     * @param attributes 自定义属性
     */
    function withAttributes(attributes: CookieAttributes): CookieApi;

    interface CookieApi {
        /**
         * 设置 cookie
         * @param name cookie 名称
         * @param value cookie 值
         * @param options cookie 选项
         */
        set(name: string, value: string, options?: CookieAttributes): void;

        /**
         * 设置 cookie（对象形式）
         * @param name cookie 名称
         * @param value cookie 值（任意对象，将被转换为 JSON）
         * @param options cookie 选项
         */
        set(name: string, value: any, options?: CookieAttributes): void;

        /**
         * 获取指定名称的 cookie 值
         * @param name cookie 名称
         */
        get(name: string): string | undefined;

        /**
         * 获取所有 cookie
         */
        get(): { [key: string]: string };

        /**
         * 获取指定名称的 cookie 值（JSON 解析）
         * @param name cookie 名称
         */
        getJSON<T = any>(name: string): T | undefined;

        /**
         * 移除指定 cookie
         * @param name cookie 名称
         * @param options cookie 选项（需与设置时一致）
         */
        remove(name: string, options?: CookieAttributes): void;

        /**
         * 移除多个 cookie
         * @param names cookie 名称数组
         * @param options cookie 选项
         */
        remove(...names: string[]): void;
    }

    // 导出默认实现
    const Cookies: CookieApi & {
        withAttributes(attributes: CookieAttributes): CookieApi;
        defaults: CookieAttributes;
    };

    export default Cookies;
}