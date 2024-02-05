import React from 'react';
import { CustomFmThemeType } from '../theme/colors/type';
interface Props {
    children: React.ReactNode;
    theme?: CustomFmThemeType;
    darkTheme?: CustomFmThemeType;
    mode?: 'light' | 'dark';
    language?: string;
}
declare const FmProvider: ({ children, theme, language, darkTheme, mode }: Props) => import("@emotion/react/jsx-runtime").JSX.Element;
export { FmProvider };
