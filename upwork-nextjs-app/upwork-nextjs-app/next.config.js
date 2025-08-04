/** @type {import('next').NextConfig} */
const nextConfig = {
  experimental: {
    appDir: true,
  },
  images: {
    domains: [],
  },
  // Enable SWC minification for better performance
  swcMinify: true,
  // Enable compression
  compress: true,
  // Enable source maps in development
  productionBrowserSourceMaps: false,
  // Internationalization support
  i18n: {
    locales: ['en', 'ru'],
    defaultLocale: 'en',
    localeDetection: true,
  },
  // Optimize bundle size
  webpack: (config, { dev, isServer }) => {
    // Optimize bundle size
    if (!dev && !isServer) {
      config.optimization.splitChunks = {
        chunks: 'all',
        cacheGroups: {
          vendor: {
            test: /[\\/]node_modules[\\/]/,
            name: 'vendors',
            chunks: 'all',
          },
        },
      };
    }
    return config;
  },
};

module.exports = nextConfig; 